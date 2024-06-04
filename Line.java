//Practical assignment 3
//Student Number: Kieran Woodrow
//Student Name: u19304308

public class Line {
    
    CardUser[] Lines;   

    public Line(int numLines, ATM atm)
    {
        Lines = new CardUser[numLines];

        for(int i = 0; i < Lines.length; i++)
        {
            Lines[i] = new CardUser(atm);
        }
    }

    public void setLines(CardUser [] lines)
    {
        for(int i = 0 ; i <lines.length; i++)
        {
            Lines[i] = lines[i];
        }
    }

    public CardUser[] getLines()
    {
        return Lines;
    }

}