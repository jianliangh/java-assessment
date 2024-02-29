package encoder;

public class Encoder {

    /**
     * @param plainText
     * @param args the command line arguments
     * @return 
     */
    
    static public String encode (String plainText){
        
        // Reference char array
        char[] reference = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890()*+,-./".toCharArray();
        
        // Reference char array length
        int end = reference.length;
        
        // Set Offset character for shiftDown
        char offsetShiftDown = 'G';
        
        // Get offset character position for shiftDown
        int shiftDown = 0;
        for (int position = 0; position  < end; position ++) {
            if (offsetShiftDown == reference[position]){
                shiftDown = position;
                break;
            }
        }
        
        // Plain Text
        char[] letters = plainText.toCharArray();
        String encodeText = String.valueOf(offsetShiftDown);
        
        // Encoding plainText
        for (char letter : letters) {
            int encodePosition = 0;
            boolean notFound = true;
            for (int position = 0; position < end; position++) {
                if (letter == reference[position]){
                        notFound = false;
                        encodePosition = position - shiftDown;
                        break;
                    }
                }      
            char encodeLetter;
            if (notFound){
                encodeLetter = letter;
            } else {
                if (encodePosition < 0){
                    encodePosition = end + encodePosition;
                }
                encodeLetter = reference[encodePosition];
            } 
            encodeText += String.valueOf(encodeLetter);
        }
        
        // Return encodeText result
        return(encodeText);
    }
    
    static public String decode (String encodedText){
        // Reference Tables
        char[] reference = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890()*+,-./".toCharArray();
        
        // Reference characters array length
        int end = reference.length;
        
        // Encode Text
        char[] letters = encodedText.toCharArray();
        
        // Get offset character for shiftUp
        char offsetShiftUp = letters[0];      
        
        // Get offset character position for shiftUp
        int shiftUp = 0;
        for (int position = 0; position  < end; position ++) {
            if (offsetShiftUp  == reference[position]){
                shiftUp = position;
                break;
            }
        }
        
        // Encode Text without offset character
        letters = encodedText.substring(1).toCharArray();
        String decodeText = "";
        
        // Decoding encodeText
        for (char letter: letters) {
            int decodePosition = 0;
            boolean notFound = true;
            for (int position = 0; position < end; position++) {
                if (letter == reference[position]){
                        notFound = false;
                        decodePosition = position + shiftUp;
                        break;
                    }
                }      
            char decodeLetter;
            if (notFound){
                decodeLetter = letter;
            } else {
                if (decodePosition > end){
                    decodePosition = decodePosition - end;
                }
                decodeLetter = reference[decodePosition];
            } 
            decodeText += String.valueOf(decodeLetter);
        }
        
        // Return encodeText result
        return(decodeText);
        
    }
    
    public static void main(String[] args) {
        
        // Encoder Methods
        System.out.println("Using Methods - Offset Character(G)");
        System.out.println("Encode Text");
        System.out.println("Before:  HELLO WORLD");
        System.out.println("After:  " + encode("HELLO WORLD"));
        System.out.println("Decode Text");
        System.out.println("Before: GB.FFI QILF-");
        System.out.println("After:   " + decode("GB.FFI QILF-"));
        
        System.out.println("");
        
        // EncoderOPP Class Methods
        EncoderOOP encoder = new EncoderOOP();
        System.out.println("Using OOP - Offset Character(F)");
        System.out.println("Encode Text");
        System.out.println("Before:  HELLO WORLD");
        System.out.println("After:  " + encoder.encode("HELLO WORLD"));
        System.out.println("Decode Text");
        System.out.println("Before: FC/GGJ RJMG.");
        System.out.println("After:   " + decode("FC/GGJ RJMG."));
        
    }
    
}
