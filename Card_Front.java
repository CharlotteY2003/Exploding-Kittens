import java.util.Scanner;
public class Card_Front
{
    public static void main(String args[])
    {
        System.out.println("Do you want to play Exploding Kittens? Please type yes or no");
        Scanner response = new Scanner(System.in);
        String answer = response.nextLine();
        
        boolean restart = false;
        while(answer.equalsIgnoreCase("yes"))
        {
            Card c1 = new Card();
            
            //*Game begins* //
            
            //*First player begins *//
            while(!restart)
            {
            c1.showCards();
            System.out.println("There is a " + c1.calculate() + "% chance to get the Exploding Kitten");
            System.out.println("Do you want to attack or draw from pile?");
            String answer2 = response.nextLine();
            int intAns = -1;
            if(!restart)
            {
            while(answer2.equalsIgnoreCase("attack") && c1.getP1Size() !=0)
            {
                System.out.println("Choose a card to attack");
                intAns = response.nextInt();
                answer2 = response.nextLine();
                c1.attack(intAns);
                while(!c1.getVerify())
                {
                    System.out.println("Choose another card to attack");
                    intAns = response.nextInt();
                    answer2 = response.nextLine();
                    c1.attack(intAns);
                }
                c1.setVerify();
                c1.showCards();//Update
                System.out.println("Do you want to attack or draw from pile? Please pick draw if you have played the" + 
                " attacked card or skip card");
                answer2 = response.nextLine(); //Find way to create new variable
            }
            if(answer2.equalsIgnoreCase("draw") || c1.getP1Size() ==0 && !(intAns == 3 || intAns ==4 || intAns ==1))
            {
                //Fix situtaion where after player 1 attacks or skips they still draw a card
                c1.draw(c1.getP1());
                if(c1.getDead())
                {
                    System.out.println("Game over! You have lost!");
                    restart = true; // find way to bring it to end
                }
                if(c1.getExplode())
                {
                   int pos = response.nextInt();
                   c1.chooseExplode(pos);
                   while(!c1.getVerify())
                   {
                       System.out.println("That is an invalid index for the deck. Please choose another index");
                       intAns = response.nextInt();
                       c1.chooseExplode(pos);
                   }
                   c1.setVerify();
                   System.out.println("Player 1 has put the exploding kitten back into the deck");
                }
            }
        }
            
            //*Second Player begins *//
            if(!restart)
            {
            int count = 1;
            if(intAns == 3)
                {
                    count =2;
                }
            
            while(count > 0 && !restart)
                {
                int card = c1.decide();
                if(card != -1)
                    {
                        c1.P2attack(card);// add something to print out if computer has drawn
                    }
                if(card ==3 || card==4)
                {
                    count =0;
                }
                else
                {
                    c1.draw(c1.getP2());
                    if(c1.getDead())
                    {
                    System.out.println("Game over! The opponent has lost!");
                    restart = true; // find way to bring it to end
                    }
                    System.out.println("Player 2 has drawn");
                    count--;
                }
                }
            }
            }
            
            
            System.out.println("Do you want to play Exploding Kittens? Please type yes or no");
            answer = response.nextLine();
            
        }
    }
}