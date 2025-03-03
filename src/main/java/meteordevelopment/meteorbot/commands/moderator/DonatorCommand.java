package meteordevelopment.meteorbot.commands.moderator;

import kong.unirest.Unirest;
import meteordevelopment.meteorbot.Config;
import meteordevelopment.meteorbot.MeteorBot;
import meteordevelopment.meteorbot.commands.Category;
import meteordevelopment.meteorbot.commands.Command;
import meteordevelopment.meteorbot.utils.Utils;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class DonatorCommand extends Command {
    public DonatorCommand() {
        super(Category.Moderator, "Pinged person gets donator.", "donator");
    }

    @Override
    public void run(MessageReceivedEvent event) {
        event.getMessage().delete().queue();
        if (!Utils.onlyMod(event)) return;

        String[] split = event.getMessage().getContentRaw().split(" ");
        String id = null;

        if (split.length > 1) {
            id = split[1];
        }

        if (id == null) return;

        Member member;
        try {
            member = event.getGuild().retrieveMemberById(id).complete();
        } catch (Exception ignored) {
            return;
        }

        MeteorBot.GUILD.addRoleToMember(member, MeteorBot.DONATOR_ROLE).queue(unused -> {
            event.getChannel().sendMessage(member.getAsMention() + " thanks for donating to Meteor Client. You can go to https://meteorclient.com and create an account, link your Discord and Minecraft accounts to get a cape. You can also upload a custom one. Also be sure send your Minecraft name so we can give you donator role on our pvp server. (pvp.meteorclient.com)").queue();

            Unirest.post("https://meteorclient.com/api/account/giveDonator")
                .header("Authorization", Config.TOKEN)
                .queryString("id", member.getId())
                .asEmpty();
        });
    }
}
