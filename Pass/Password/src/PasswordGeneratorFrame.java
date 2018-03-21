/**
 * @author Rick Morris
 * This class builds the frame and generates string on button press
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class PasswordGeneratorFrame extends JFrame implements ActionListener{

    private JComboBox passwordLength = new JComboBox();
    private JCheckBox lowerCaseOption = new JCheckBox("a,b,c,d,e....");
    private JCheckBox upperCaseOption = new JCheckBox("A,B,C,D,E....");
    private JCheckBox specialOption = new JCheckBox("^&%$#....");
    private JButton generatePassword = new JButton("Generate Password");
    private JTextField generatedPassword = new JTextField("Your password generates here!");
    private String[] lowerCaseCharacters;
    private String[] upperCaseCharacters;
    private String[] specialCharacters;
    private ArrayList<String> availableCharacters;

    public PasswordGeneratorFrame(){
        super("PasswordGenerator");
        setLookAndFeel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200, 300);
        setVisible(true);
        BoxLayout box = new BoxLayout(getContentPane(),BoxLayout.Y_AXIS);
        setLayout(box);


        add(this.passwordLength);
        add(this.lowerCaseOption);
        add(this.upperCaseOption);
        add(this.specialOption);
        add(this.generatePassword);
        add(this.generatedPassword);

        addPossibleLengths();
        this.generatePassword.addActionListener(this);

        this.lowerCaseCharacters = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t",
        "u","v","w","x","y","z"};
        this.upperCaseCharacters = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q",
        "R","S","T","U","V","W","X","Y","X"};
        this.specialCharacters = new String[]{"!","@","#","$","%","^","&","*","(",")","/"};
    }

    /**
     * Set look and feel of app
     */
    private void setLookAndFeel(){
        try {
            UIManager.setLookAndFeel(
                    "java.swing.plaf.nimbus.NimbusLookAndFeel"
            );
        } catch(Exception exc) {

        }
    }

    /**
     * Populate Combobox with possible Int values via for loop
     */
    public void addPossibleLengths(){
        for(int i = 0; i < 19; i++){
            this.passwordLength.addItem(i);
        }
    }

    /**
     * This method will check if the respective checkbox is checked and add the
     * corresponding array values to a list if that's the case
     * The Text field will be set at the end
     * @param e is the event that triggered this method
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.availableCharacters = new ArrayList<String>();

        if(this.lowerCaseOption.isSelected() == true) {
            for(int i = 0; i < this.lowerCaseCharacters.length; i++){
                this.availableCharacters.add(this.lowerCaseCharacters[i]);
            }
        }

        if(this.upperCaseOption.isSelected() == true){
            for(int i = 0; i < this.upperCaseCharacters.length; i++){
                availableCharacters.add(this.upperCaseCharacters[i]);
            }
        }

        if(this.specialOption.isSelected() == true){
            for(int i = 0; i < this.specialCharacters.length; i++){
                availableCharacters.add(this.specialCharacters[i]);
            }
        }

        this.generatedPassword.setText(generateString());
    }

    /**
     * This method uses a for loop and gets a random value from the list of possible values
     *  then appends them to the end of the password string.
     * @return password when the correct amount of values have been appended
     */
    public String generateString(){
        String password = new String("");
        Random rn = new Random();

        for(int i = 0; i < (int)this.passwordLength.getSelectedItem(); i++){
            int randomValue = rn.nextInt(this.availableCharacters.size());
            password += this.availableCharacters.get(randomValue);
        }

        return password;
    }

}
