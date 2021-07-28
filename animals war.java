import java.util.Scanner;
import java.lang.Math;
class Pos
{
    int x;
    int y;
}
class Mohre
{
    Pos pos=new Pos();
    int pow;
    int dis;
    char name;
    Mohre(int pow,int dis,char name,int x,int y)
    {  
      this.pow=pow;
      this.dis=dis;
      this.name=name;
      this.pos.x=x;
      this.pos.y=y;
    }
}
class Bazikon
{   
    Mohre tiger,elephant,horse;
    Bazikon(int d,int d1,int d2,int p,int p1,int p2,char name,char name1,char name2,int x,int x1,int x2,int y,int y1,int y2)
    {
        tiger=new Mohre(p,d,name,x,y);
        elephant=new Mohre(p1,d1,name1,x1,y1);
        horse=new Mohre(p2,d2,name2,x2,y2);
    }
    int search(Mohre b,int x1,int y1,Mohre z[][])
    {
        int i;
        if(b.name>='a'&&b.name<='z')
            i=1;
        else
            i=0;
        int j=0;
        if(z[x1][y1]!=null)
        {
            if(z[x1][y1].name>='a'&&z[x1][y1].name<='z')
                j=1;
            else
                j=0;
        }
        if(z[x1][y1]==null)    
            return 0;
        else
        {
            if((z[x1][y1].pow<=b.pow)&&(i!=j))                
                return 1;           
            else                            
                return -1;
        }
    }
    void harekat(char a,Mohre b,int l,Mohre z[][])
    {
        int x1=b.pos.x;
        int y1=b.pos.y;
        if(a=='u')
            x1-=l;
        else if(a=='d') 
            x1+=l;
        else if(a=='r')
            y1+=l;
        else if(a=='l')
            y1-=l;
        if((x1>=0&&x1<=2)&&(y1>=0&&y1<=2))
        {
            if(search(b,x1,y1,z)==-1)
                System.out.println("harekat gheyr mojaz!");
            else if(search(b,x1,y1,z)==0)
            {
                z[x1][y1]=b;
                z[b.pos.x][b.pos.y]=null;
                z[x1][y1].pos.x=x1;
                z[x1][y1].pos.y=y1;
            }
            else if(search(b,x1,y1,z)==1)
            {
                z[x1][y1]=b;
                z[b.pos.x][b.pos.y]=null;
                z[x1][y1].pos.x=x1;
                z[x1][y1].pos.y=y1;
            }
        }
        else
            System.out.println("waaaaarning!");      
    }
}
class Bazi
{
    Scanner input=new Scanner(System.in);
    Bazikon bazikon1=new Bazikon(1,2,1,3,2,1,'t','e','h',0,0,0,2,0,1);
    Bazikon bazikon2=new Bazikon(1,2,1,3,2,1,'T','E','H',2,2,2,2,0,1);
    Mohre[][]z={{bazikon1.elephant,bazikon1.horse,bazikon1.tiger},{null,null,null},{bazikon2.elephant,bazikon2.horse,bazikon2.tiger}};  
    void print()
    {
        int i,j;
        int k=0;
        for(i=0;i<z.length;i++)
        {
            for(j=0;j<z.length;j++)
            {
                if(z[i][j]!=null)
                {
                    System.out.print(z[i][j].name+"\t");
                    k++;
                    if(k%3==0)
                        System.out.print("\n");
                }
                else
                {
                     System.out.print("*\t");
                     k++;
                     if(k%3==0)
                        System.out.print("\n");
                }
            }
        }
    }
    int search()
    {
        int i,j;
        int k=0;
        int b=0;
        for (i = 0; i < z.length; i++) 
        {
            for (j = 0; j < z.length; j++)
            {
                if(z[i][j]!=null)
                {
                    if(z[i][j].name>='a'&&z[i][j].name<='z')
                        k++; 
                    else if(z[i][j].name>='A'&&z[i][j].name<='Z')
                        b++;
                }
            }
        }
        if(k==0&&b!=0)
        {
            System.out.println("player 2 winnnnnnner!");
            return 2;
        }
        else if(k!=0&&b==0)
        {
            System.out.println("player 1 winnnnnnner!");
            return 1;
        }   
        else
            return 0;
    } 
    void player1()
    {
        char n;
        while(true)
        {
            System.out.print("Player 1 choose mohre(e h t):\n");
            n=input.next().charAt(0);
            if(n=='e'||n=='h'||n=='t')
                break;
            else
                System.out.println("lotfan yeki az character haye mored nazar ra vared konid!");
        }
        System.out.println("Choose direction: (L U R D) :");
        char m=input.next().charAt(0);
        System.out.println("Choose Distance:");
        int l=input.nextInt();
        int i,j;
        boolean w=false;
        int k=0;
        int f=0;
        for(i=0;i<z.length;i++)
        {
            if(w==true)
                break;
            for(j=0;j<z.length;j++)
            {
                if(z[i][j]!=null)
                {
                    f++;
                    if(z[i][j].name==n)
                    {
                        if(z[i][j].dis>=l)
                        {
                            bazikon1.harekat(m,z[i][j],l,z);
                            w=true;
                            break;
                        }
                        else
                            System.out.println("distance gheyr mojaz!");
                    }
                    else
                      k++;  
                }
            }
        }
        if(f==k)
            System.out.println("mohre mored nazar vojod nadarad!");
        print();
    }
    void player2()
    {
        char n;
        while(true)
        {
            System.out.print("Player 2 choose mohre(E H T):\n");
            n=input.next().charAt(0);
            if(n=='E'||n=='H'||n=='T')
                break;
            else
                System.out.println("lotfan yeki az character haye mored nazar ra vared konid!");
        }
        System.out.println("Choose direction: (L U R D) :");
        char m=input.next().charAt(0);
        System.out.println("Choose Distance:");
        int l=input.nextInt();
        int i,j;
        boolean w=false;
        int k=0;
        int f=0;
        for(i=0;i<z.length;i++)
        {
            if(w==true)
                break;
            for(j=0;j<z.length;j++)
            {
                if(z[i][j]!=null)
                {
                    f++;
                    if(z[i][j].name==n)
                    {
                        if(z[i][j].dis>=l)
                        {
                            bazikon2.harekat(m,z[i][j],l,z);
                            w=true;
                            break;
                        }
                        else
                            System.out.println("distance gheyr mojaz!");
                    }
                    else
                        k++;
                }
            }
        }
        if(f==k)
            System.out.println("mohre mored nazar vojod nadarad!");
        print();
    }
    void random(int a)
    {
        if(a%2==0)
        {
            while(true)
            {
                player1();
                if(search()==1||search()==2)
                    break;
                player2();
                if(search()==1||search()==2)
                    break;
            }
        }
        else
        {
            while(true)
            {
                player2();
                if(search()==1||search()==2)
                    break;
                player1();
                if(search()==1||search()==2)
                    break;
            }
        }
    }
}
public class Main
{
    public static void main(String[] args) 
    {
       Bazi bazi=new Bazi();
       double a=Math.random()*10;
       int b=(int)a;
       bazi.print();
       bazi.random(b);
    }
}