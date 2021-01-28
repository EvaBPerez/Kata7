package kata7.apps.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import kata7.model.Block;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import kata7.control.BlockPresenter;
import kata7.control.Command;
import kata7.control.DownCommand;
import kata7.control.LeftCommand;
import kata7.control.RightCommand;
import kata7.control.UpCommand;
import kata7.view.BlockDisplay;

public class Main extends JFrame {
    private static final int BLOCK_SIZE = 100;

    public static void main(String[] args) {
        new Main().execute();
    }
    
    private BlockDisplay blockDisplay;
    private Map<String,Command> commands = new HashMap<>();
    private BlockPresenter blockPresenter;
    
    public Main() {
        this.setTitle("Block shifter");
        this.setSize(700, 762);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(), BorderLayout.SOUTH);
     
        Block block = new Block(4, 4);
        blockPresenter = new BlockPresenter(block, blockDisplay);
        commands.put("left", new LeftCommand(block));
        commands.put("right", new RightCommand(block));
        commands.put("down", new DownCommand(block));
        commands.put("up", new UpCommand(block));
        
    }

    private void execute() {
        this.setVisible(true);
    }

    private BlockPanel blockPanel() {
        BlockPanel blockPanel = new BlockPanel(BLOCK_SIZE, Block.MAX);
        this.blockDisplay = blockPanel;
        return blockPanel;
    }

    private JMenuBar toolbar() {
        JMenuBar toolbar = new JMenuBar();
        toolbar.add(button("left"));
        toolbar.add(button("right"));
        toolbar.add(button("down"));
        toolbar.add(button("up"));
        return toolbar;
    }

    private JButton button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                commands.get(name).execute();
            }
        });
        return button;
    }
    
}
