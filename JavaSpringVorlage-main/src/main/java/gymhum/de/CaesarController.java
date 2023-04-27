package gymhum.de;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CaesarController {

    char[] letters;
    String cleartext;
    String encryptedtext;
    int key;
    char[] cleartextletters;

    public CaesarController(){
        fillalphabet();
        setEncryptedtext("Hier steht der verschlüsselte Text");
        setCleartext("An");
        setKey(1);
        Encryption();
    }

    @GetMapping("/caesar")
    public String caesar(@RequestParam(name="activePage", required = false, defaultValue = "caesar") String activePage, @RequestParam(name="encryptedtext", required = false, defaultValue = "Test") String encryptedtext, Model model){
        model.addAttribute("encryptedtext", getEncryptedtext());
        model.addAttribute("activePage", "caesar");
        return "index.html";
    }

    @GetMapping("/caesarencrypting")
    public RedirectView caesarencrypting(@RequestParam(name="activePage", required = false, defaultValue = "caesar") String activePage, @RequestParam(name="cleartext", required = true, defaultValue = "null") String cleartext, @RequestParam(name="key", required = true, defaultValue = "0" ) int key, Model model){
        model.addAttribute("activePage", "caesar");
        setCleartext(cleartext);
        setKey(key);
        return new RedirectView("/caesar");
    }

    @GetMapping("/caesardecrypting")
    public RedirectView caesardecrypting(@RequestParam(name="activePage", required = false, defaultValue = "caesar") String activePage, @RequestParam(name="encryptedtext", required = true, defaultValue = "null") String encryptedtext, @RequestParam(name="key", required = true, defaultValue = "0" ) int key, Model model){
        model.addAttribute("activePage", "caesar");
        setEncryptedtext(encryptedtext);
        setKey(key);
        return new RedirectView("/caesar");
    }


    private void fillalphabet(){
        char[] letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',};
        setLetters(letters);
    }

    private void Encryption(){
        char[] cleartextletters = cleartext.toCharArray(); 

        for(char eachletter: cleartextletters){
            for(int lettervalue = 0; lettervalue < 26; lettervalue++){
                if(eachletter == letters[lettervalue]){
                    System.out.println(letters[lettervalue]);
                    if(lettervalue + key >= 26){
                        int steps = (lettervalue+key)%26;
                        eachletter = getLetters()[steps];
                        System.out.println(getLetters()[steps]);
                    }
                    else{
                        eachletter = getLetters()[lettervalue + key];
                        System.out.println(getLetters()[lettervalue+key]);
                        System.out.println("unter 26");
                    }
                }
            }
        }
        setEncryptedtext(String.valueOf(cleartextletters));
        System.out.println(cleartext);
        System.out.println(encryptedtext);
    }
    private void Decryption(){
        char[] cleartextletters = cleartext.toCharArray(); 

        for(char eachletter: cleartextletters){
            for(int lettervalue = 0; lettervalue < 26; lettervalue++){
                
            }
        }
    }


    public void setCleartext(String cleartext) {
        this.cleartext = cleartext;
    }
    public void setCleartextletters(char[] cleartextletters) {
        this.cleartextletters = cleartextletters;
    }
    public void setKey(int key) {
        this.key = key;
    }
    public void setEncryptedtext(String encryptedtext) {
        this.encryptedtext = encryptedtext;
    }
    public void setLetters(char[] letters) {
        this.letters = letters;
    }

    public String getCleartext() {
        return cleartext;
    }
    public char[] getCleartextletters() {
        return cleartextletters;
    }
    public int getKey() {
        return key;
    }
    public String getEncryptedtext() {
        return encryptedtext;
    }
    public char[] getLetters() {
        return letters;
    }
    

}
