package encoder;

public class EncoderOOP {
    // Reference char array
    private char[] reference = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890()*+,-./".toCharArray();
    
    // Reference characters array length
    private final int end = reference.length;
    
    // Offset ShiftDown Character
    private final char offsetShiftDown = 'F';
    
    // Encode plainText
    public String encode (String plainText){
        
        // Get offset character position for shiftDown
        int shiftDown = 0;
        
        for (int position = 0; position  < this.end; position ++) {
            if (this.offsetShiftDown == this.reference[position]){
                shiftDown = position;
                break;
            }
        }
        
        // Plain Text
        char[] letters = plainText.toCharArray();
        String encodeText = String.valueOf(this.offsetShiftDown);
        
        // Encoding plainText
        for (char letter : letters) {
            int encodePosition = 0;
            boolean notFound = true;
            for (int position = 0; position < this.end; position++) {
                if (letter == this.reference[position]){
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
                    encodePosition = this.end + encodePosition;
                }
                encodeLetter = this.reference[encodePosition];
            } 
            encodeText += String.valueOf(encodeLetter);
        }
        
        // Return encodeText result
        return(encodeText);
    }
    
    // Decode encodedText
    public String decode (String encodedText){
        
        // Encode Text
        char[] letters = encodedText.toCharArray();
        
        // Get offset character for shiftUp
        char offsetShiftUp = letters[0]; 
        
        // Get offset character position for shiftUp
        int shiftUp = 0;
        
        for (int position = 0; position  < this.end; position ++) {
            if (offsetShiftUp == this.reference[position]){
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
            for (int position = 0; position < this.end; position++) {
                if (letter == this.reference[position]){
                        notFound = false;
                        decodePosition = position + shiftUp;
                        break;
                    }
                }      
            char decodeLetter;
            if (notFound){
                decodeLetter = letter;
            } else {
                if (decodePosition > this.end){
                    decodePosition = decodePosition - this.end;
                }
                decodeLetter = reference[decodePosition];
            } 
            decodeText += String.valueOf(decodeLetter);
        }
        
        // Return encodeText result
        return(decodeText);
    
    }
    
}
