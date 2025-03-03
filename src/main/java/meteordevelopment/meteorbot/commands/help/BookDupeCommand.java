package meteordevelopment.meteorbot.commands.help;

import meteordevelopment.meteorbot.commands.Category;
import meteordevelopment.meteorbot.commands.Command;
import meteordevelopment.meteorbot.utils.Utils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class BookDupeCommand extends Command {
    public BookDupeCommand() {
        super(Category.Help, "Displays help about Player Rollback Book Dupe.", "bookdupe");
    }

    @Override
    public void run(MessageReceivedEvent event) {
        event.getMessage().delete().queue();
        event.getChannel().sendMessage(Utils.embed("The Player Rollback dupe is **PATCHED** on any server that isn't vanilla. If you still want to try it, then download the latest dev build and download the appropriate addon \n[BookDupe](https://github.com/MeteorDevelopment/meteor-book-dupe-addon/releases/latest)").build()).queue();
    }
}
