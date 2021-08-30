import java.util.ArrayList;
public class Card
{
    ArrayList<Integer> deck = new ArrayList<Integer>();
    // 1= Exploding Kitten
    // 2= defuse
    // 3 = attack
    // 4 = skip
    // 5 = favor
    // 6 = shuffle
    // 7 = see the future
    // 8 = taco cat
    // 9 = catermelon
    // 10 = hairy potter
    //11 = beardcat
    // 12 = rainbow cat
    
    //Need reaction method to detect which card the opponent has placed
    ArrayList<Integer> p1 = new ArrayList<Integer>();
    ArrayList<Integer> p2 = new ArrayList<Integer>();
    boolean exploding = false;
    boolean dead = false;
    boolean cat = false;
    boolean pickFavor = false;
    //*Initialization of deck*//
    public Card()
    {
        setDeck();
        shuffle();
        setP1();
        setP2();
        int index = (int)(Math.random()*deck.size());
        deck.add(index, 1);
        for(int i=0; i<2; i++)
        {
            index = (int)(Math.random()*deck.size());
            deck.add(index, 2);
        }
    }
    public void setP1()
    {
        for(int i=0; i<7; i++)
        {
            int index = (int)(Math.random()*deck.size());
            p1.add(deck.remove(index));
        }
        p1.add(2);
    }
    public void setP2()
    {
        for(int i=0; i<7; i++)
        {
            int index = (int)(Math.random()*deck.size());
            p2.add(deck.remove(index));
        }
        p2.add(2);
    }
    public void setDeck()
    {
        //deck.add(1);
        
        for(int i=0; i<4; i++)
        {
            deck.add(3);
            deck.add(4);
            deck.add(5);
            deck.add(6);
            deck.add(8);
            deck.add(9);
            deck.add(10);
            deck.add(11);
            deck.add(12);
        }
        for(int i=0; i< 5; i++)
        {
            deck.add(7);
        }
        
    }
    public boolean isTaken(ArrayList<Integer> list, int index)
    {
        for(int i=0; i< list.size(); i++)
        {
            if(list.get(i) == index)
            {
                return true;
            }
        }
        return false;
    }
    public void shuffle()
    {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        ArrayList<Integer> taken = new ArrayList<Integer>();
        for(int i=0; i< deck.size(); i++)
        {
            int index = (int)(Math.random()*deck.size());
            while(isTaken(taken, index))
            {
                index = (int)(Math.random()*deck.size());
            }
            arr.add(deck.get(index));
            taken.add(index);
        }
        deck = arr;
    }
    public int getP1Size()
    {
        return p1.size();
    }
    public ArrayList<Integer> getP1()
    {
        return p1;
    }
    public ArrayList<Integer> getP2()
    {
        return p2;
    }
    public ArrayList<Integer> getDeck()
    {
        return deck;
    }
    public int getDeckSize()
    {
        return deck.size();
    }
    public String getName(ArrayList<Integer> list)
    {
        if(list == p1)
        {
            return "P1";
        }
        else
        {
            return "P2";
        }
    }
    
    //* Playing the game *//
    
    
    
    boolean success = true;
    
    public double calculate()
    {
        double percent = ((double)1/deck.size()) * 100;
        return percent;
    }
    public boolean isThere(int num, ArrayList<Integer> object)
    {
        for(int i=0; i< object.size(); i++)
        {
            if(object.get(i) == num)
            {
                return true;
            }
        }
        return false;
    }
    public int getIndex(int num, ArrayList<Integer> object)
    {
        for(int i=0; i< object.size(); i++)
        {
            if(object.get(i) == num)
            {
                return i;
            }
        }
        return -1;
    }
    public boolean getVerify()
    {
        return success;
    }
    public void setVerify()
    {
        success = true;
    }
    public void chooseAttack(int num)
    {
        if (num >12 || num <= 2 || !(isThere(num, p1)))
        {
            System.out.println("The card you want to use does not exist in your playing hand. Please type in another card");
            success = false;
            //find way to change num
        }
        else if(num == 3)
        {
            System.out.println("You have chosen the attack card");
            p1.remove(getIndex(num, p1));
            success = true;
        }
        else if(num==2)
        {
            System.out.println("Sorry, you can't use a defuse card to attack. Please pick another card");
            success = false;
            //find way to change num
        }
        else if(num == 4)
        {
            System.out.println("You have chosen the skip card");
            p1.remove(getIndex(num, p1));
            success = true;
        }
        else if(num ==5)
        {
            System.out.println("You have chosen the favor card");
            p1.add(pickP2());
            p1.remove(getIndex(num, p1));
            success = true;
        }
        else if(num == 6)
        {
            System.out.println("You have chosen the shuffle card");
            shuffle();
            p1.remove(getIndex(num, p1));
            success = true;
        }
        else if(num ==7)
        {
            System.out.println("You have chosen the see the future card");
            if(deck.size() < 3)
            {
                System.out.println("Here are the cards of the deck");
                for(int i=0; i< deck.size(); i++)
                {
                    System.out.print(deck.get(i) + " ");
                }
            }
            else
            {
                System.out.println("Here are the first 3 cards of the deck");
                for(int i=0; i< 3; i++)
                {
                    System.out.print(deck.get(i) + " ");
                }
            }
            p1.remove(getIndex(num, p1));
            success = true;
        }
        else
        {
            if(hasPair(num, p1))
            {
                if(num ==8)
                {
                    System.out.println("You have chosen Taco Cat pair");
                }
                else if(num ==9)
                {
                    System.out.println("You have chosen catelope pair");
                }
                else if(num ==10)
                {
                    System.out.println("You have chosen hairy potter pair");
                }
                else if(num ==11)
                {
                    System.out.println("You have chosen beard cat pair");
                }
                else
                {
                    System.out.println("You have chosen rainbow cat pair");
                }
                p1.remove(getIndex(num, p1));
                p1.remove(getIndex(num, p1));
                int newCard = pickRandom(p2);
                p1.add(newCard);
                System.out.println("You have received the new card of " + newCard);
                success = true;
            }
            else
            {
                System.out.println("Sorry. You don't have a pair. Please pick another card");
                success = false;
                //find way to change num
            }
        }
    }
    public void attack(int num)
    {
        //showCards();
        //System.out.println("Choose an attack");
        chooseAttack(num);
        
        
    }
    public void showCards()
    {
        System.out.println("Here are all your cards");
        System.out.println(p1);
    }
    public void draw(ArrayList<Integer> object)
    {
        int newCard = deck.remove(0);
        
        if(newCard ==1)
        {
            System.out.println("Oh no! " + getName(object) + " has drawn the exploding kitten!");
            if(isThere(2, object))
            {
                getDefuse(object);
                //something to end turn
            }
            else
            {
                dead = true;
            }
        }
        else
        {
            object.add(newCard);
            if(object==p2)
            {
                System.out.println("Player 2 has drawn");
            }
        }
    }
    public void getDefuse(ArrayList<Integer> object)
    {
        object.remove(getIndex(2, object));
        System.out.println(getName(object) + " has used 1 defuse card");
        System.out.println(getName(object) + " needs to put the exploding kitten back in the deck");
        if(object == p2)
        {
            int index = (int)(Math.random()*2);
            if(index==0)
            {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                if(deck.size() == 0)
                {
                    temp.add(0);
                }
                else if(deck.size() < 5)
                {
                    for(int i=0; i< deck.size(); i+=2)
                    {
                        temp.add(i);
                    }
                }
                else
                {
                    for(int i=0; i< 5; i+=2)
                    {
                        temp.add(i);
                    }
                }
                
                index = (int)(Math.random()*temp.size());
                deck.add(temp.get(index), 1);
                
            }
            else
            {
                index = (int)(Math.random() * deck.size());
                deck.add(index, 1);
                
            }
            System.out.println(getName(object) + " has put the exploding kitten back into the deck");;
        }
        else
        {
            System.out.println("Where do you want to put the exploding kitten?");
            exploding = true;
            //add method for Scanner input
            // add another boolean to indicate need defuse?
        }
    }
    public boolean getDead()
    {
        return dead;
    }
    public void chooseExplode(int num)
    {
        if(deck.size() == 0)
        {
            if(num != 0)
            {
                System.out.println("Sorry, there is only 1 place to put the card in the deck, we have already done that for you");
            }
            deck.add(1);
            exploding  = false;
            success = true;
        }
        else
        {
            if(num > deck.size()-1 || num < 0)
        {
            success = false;
        }
        else
        {
            deck.add(num, 1);
            success = true;
            exploding = false;
        }
        }
        
        
    }
    public boolean getExplode()
    {
        return exploding;
    }
    public int pickP2()
    {
        if(p2.size() > 0)
        {
        if(isThere(8, p2))
        {
            return p2.remove(getIndex(8, p2));
        }
        else if(isThere(9, p2)) 
        {
            return p2.remove(getIndex(9, p2));
        }
        else if(isThere(10, p2))
        {
            return p2.remove(getIndex(10, p2));
        }
        else if(isThere(11, p2))
        {
            return p2.remove(getIndex(11, p2));
        }
        else if(isThere(12, p2))
        {
            return p2.remove(getIndex(12, p2));
        }
        else if(isThere(6, p2))
        {
            return p2.remove(getIndex(6, p2));
        }
        else if(isThere(5, p2))
        {
            return p2.remove(getIndex(5,p2));
        }
        else if(isThere(7, p2))
        {
            return p2.remove(getIndex(7,p2));
        }
        else if(isThere(3, p2))
        {
            return p2.remove(getIndex(3,p2));
        }
        else if(isThere(4, p2))
        {
            return p2.remove(getIndex(4,p2));
        }
        else
        {
            return p2.remove(getIndex(2,p2));
        }
    }
    else
    {
        return -1;//Find way to fix it
    }
    }
    public int pickP1(int num)
    {
        //if(!isThere(num, p1))
        //{
         //   return -1;
        //}
       pickFavor = false;
       return p1.remove(getIndex(num, p1));
    }
    public boolean hasPair(int num,ArrayList<Integer> object)
    {
        int count =0;
        for(int i=0; i< object.size(); i++)
        {
            if(object.get(i) == num)
            {
                count++;
            }
        }
        if(count >=2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public int pickRandom(ArrayList<Integer> object)
    {
        if(object.size() > 0)
        {
            int index = (int)(Math.random() * object.size());
            return object.remove(index);
        }
        else
        {
            return -1;//Find way to fix it
        }
        
    }
    public boolean getPickFavor()
    {
        return pickFavor;
    }
    public void setPickFavor()
    {
        pickFavor = false;
    }
    
    //* P2 methods *//
    
    public void P2attack(int num)
    {
        
        if(num == 3)
        {
            System.out.println("P2 has chosen the attack card");
            p2.remove(getIndex(num, p2));
        }
        
        else if(num == 4)
        {
            System.out.println("P2 has chosen the skip card");
            p2.remove(getIndex(num, p2));
        }
        else if(num ==5)
        {
            pickFavor = true;
            //p2.add(pickP1());//change method and object receiving it
            System.out.println("Oh no! P2 has chosen the favor card! Lose 1 card!");
            p2.remove(getIndex(num, p2));
            //draw(p2);
        }
        else if(num == 6)
        {
            
            shuffle();
            System.out.println("P2 has played the shuffle card");
            p2.remove(getIndex(num, p2));
            //draw(p2);
        }
        else if(num ==7)
        {
            //add on
            System.out.println("P2 has played the See the Future Card");
            p2.remove(getIndex(num, p2));
            if(deck.size() < 3)
            {
                for(int i=0; i< deck.size(); i++)
                {
                    if(deck.get(i)==1)
                    {
                        cat = true;
                        decide();
                    }
                }
            }
            else
            {
                for(int i=0; i< 3; i++)
                {
                    if(deck.get(i) == 1)
                    {
                        cat = true;
                        decide();
                   //add boolean to signal need defuse
                    }//add recursive function?
                }
            }
            
            
        }
        else
        {
            if(hasPair(num, p2))
            {
                System.out.println("Oh no! P2 has played a cat pair! Lose a card");
                p2.remove(getIndex(num, p2));
                p2.remove(getIndex(num, p2));
                //for(int i=0; i< 2; i++)
                //{
                  //  System.out.println();
                //}
                //System.out.println("P1's deck " + p1);
                //System.out.println("P2's deck " + p2);
                //for(int i=0; i< 2; i++)
                //{
                  //  System.out.println();
                //}
                int newCard = pickRandom(p1);
                //System.out.println(newCard);
                p2.add(newCard);
                //for(int i=0; i< 2; i++)
                //{
                  //  System.out.println();
                //}
                //System.out.println("P1's deck " + p1);
                //System.out.println("P2's deck " + p2);
                //for(int i=0; i< 2; i++)
                //{
                  //  System.out.println();
                //}
                //draw(p2);
            }
            
        }
    }
    public int decide()
    {
        if(cat)
        {
            
            cat = false;
            if(isThere(3, p2))
            {
                return 3;
            }
            else if(isThere(4, p2))
            {
                return 4;
            }
            else if(isThere(6, p2))
            {
                return 6;
            }
      
        }
        if(calculate() >= 37)
        {
            
            if(isThere(7, p2))
            {
                return 7;
            }
            if(isThere(6, p2))
            {
                return 6;
            }
            if(isThere(4, p2))
            {
                return 4;
            }
            if(isThere(3, p2))
            {
                return 3;
            }
            if(isThere(5, p2))
            {
                return 5;
            }
            if(hasPair(8, p2)|| hasPair(9, p2)|| hasPair(10, p2)|| hasPair(11, p2)|| hasPair(12, p2))
            {
                if(hasPair(8, p2))
                {
                    return 8; 
                }
                else if(hasPair(9, p2))
                {
                    return 9;
                }
                else if(hasPair(10, p2))
                {
                    return 10;
                }
                else if(hasPair(11, p2))
                {
                    return 11;
                }
                else
                {
                    return 12;
                }
            }
            else
            {
                return -1;
            }
        }
        else
        {
        int index = (int)(Math.random()*2);
        if(index ==0)
        {
            
            if(hasPair(8, p2)|| hasPair(9, p2)|| hasPair(10, p2)|| hasPair(11, p2)|| hasPair(12, p2))
            {
                
                if(hasPair(8, p2))
                {
                    return 8; 
                }
                else if(hasPair(9, p2))
                {
                    return 9;
                }
                else if(hasPair(10, p2))
                {
                    return 10;
                }
                else if(hasPair(11, p2))
                {
                    return 11;
                }
                else
                {
                    return 12;
                }
            }
            if(isThere(5, p2))
            {
                return 5;
            }
            int r = (int)(Math.random()*3);
            if(r==0)
            {
                if(isThere(3, p2))
                {
                    return 3;
                }
            }
            return -1;
        }
        else
        {
            return -1;
        }
    
    }
    
    }
}