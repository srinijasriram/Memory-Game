import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
class Tempo extends JFrame implements ActionListener{
JButton jb1,jb2,jb3;
JTextField jt1,jt2,jt3;
JLabel l1,l2,l3,l;
FileReader fs=new FileReader("wordsfile.txt");;
String[] arr=new String[100];
String[] arr1=new String[100];
int n=0,n1=0,n2=0,m=0,cn=0;
    Tempo() throws IOException{
     super("MEMORY GAME");
     l=new JLabel("MEMORY GAME");
     l.setFont(new Font("Calibri",Font.PLAIN,30));
     l.setBounds(300,100,300,50); 
     this.add(l);
     jb1=new JButton("play");
     jb1.setBounds(400,520,100,50);
     this.add(jb1);
     jb1.addActionListener(this);
     jb2=new JButton("enter");
     jb2.setBounds(300,520,100,50);
     this.add(jb2);
     jb2.addActionListener(this);
     jb3=new JButton("next");
     jb3.setBounds(200,520,100,50);
     this.add(jb3);
     jb3.addActionListener(this);
            l1=new JLabel("Computer Word");
            l1.setFont(new Font("Calibri",Font.PLAIN,20));
            l1.setBounds(200,250,200,50); 
            this.add(l1);
            jt1=new JTextField();
            jt1.setBounds(400,250,200,50);
            this.add(jt1);
            jt1.addActionListener(this);
            l2=new JLabel("Player Word");
            l2.setFont(new Font("Calibri",Font.PLAIN,20));
            l2.setBounds(200,350,200,50); 
            this.add(l2); 
            jt2=new JTextField();
            jt2.setBounds(400,350,200,50);
            this.add(jt2); 
            jt2.addActionListener(this); 
            l3=new JLabel("Results");
            l3.setFont(new Font("Calibri",Font.PLAIN,20));
            l3.setBounds(200,450,200,50); 
            this.add(l3);
            jt3=new JTextField("click on play to start");
            jt3.setBounds(400,450,200,50);
            this.add(jt3); 
            jt3.addActionListener(this);
            this.getContentPane().setBackground(Color.GRAY);
            this.setLayout(null);
            this.setSize(800,800);
            this.setVisible(true);
            
    }
    public void actionPerformed(ActionEvent ae){
        try{
        if(ae.getActionCommand()==jb1.getActionCommand()){

           try{
                int i=0,j=0;
                cn=0;
                boolean b=true;
                char[] c=new char[20];
                for(;b;i=0){
                    
                    c[i]=(char)fs.read();
                   cn++;
                    while(c[i]!='-'){
                     c[++i]=(char)fs.read();
                    cn++;
                    }
                    b=checkcomputer(c,n);
                }
                arr[n]="";
                while(c[j]!='-'){
                    arr[n]=arr[n]+c[j++];
                    
                }
                jt1.setText(arr[n]);
                n++;
                
           }
           catch(IOException e){
              JOptionPane.showMessageDialog(null,e+"");
           }
       }
       else if(ae.getActionCommand()==jb2.getActionCommand()){
          try{
             String s=jt2.getText();
               if(m<n){
                  if(s.equals(arr[m])){
                     jt3.setText("you entered right word");
                  }
                  else{
                    System.out.println("As you entered wrong word,The game ended");
                     sys();
                  }
               }
               m++;
               if(m==n){jt3.setText("Now you can give your own word.");m=0;}

          }catch(Exception e){
              JOptionPane.showMessageDialog(null,e+"");
           }
       }
       else if(ae.getActionCommand()==jb3.getActionCommand()){
            try{
                   if(m!=0){System.out.println("the game ended, as you not enter complete sequence.");sys();}
                   jt3.setText("you entered");
                   String s=jt2.getText();
                   boolean g=check(s,n);
                   if(g){
                          arr1[n2++]=s;
                          arr[n]="";
                          arr[n]=arr[n].replace("",s);
                          n++;
                          int i=0,j=0;
                          boolean b=true;
                          char[] c=new char[20];
                          for(;b;i=0){
                              c[i]=(char)fs.read();
                              while(c[i]!='-'){
                                c[++i]=(char)fs.read();
                              }
                              b=checkcomputer(c,n);
                          }
                          arr[n]="";
                          while(c[j]!='-'){
                              arr[n]=arr[n]+c[j++];
                          }
                          jt1.setText(arr[n]);
                          n++;
                     }
                     else{
                          System.out.println("This word is already exist,You failed The game ended");
                          sys(); 
                     }
           }
            catch(Exception e){JOptionPane.showMessageDialog(null,e+"");
            }
       }
       
     }catch(Exception e){JOptionPane.showMessageDialog(null,e+"");}

    }
    public boolean checkcomputer(char[] c,int n){
       
      int i=0,j=0,k=0;
      if(n==0){return false;}
      while(i<n){
           String s1=String.valueOf(c);
           String a=arr[i]+'-';
           for(k=0;k<a.length() && s1.charAt(k)==a.charAt(k);k++)
           if(k==a.length()-1){
               return true;
           }
           i++;
      }
      return false;
   }
    public static void main(String args[])throws IOException{
     new Tempo();
    }
   
     public boolean check(String s,int n){
      int i=0;
      while(i<n){
           if(s.equals(arr[i])){
               return false;
           }
           i++;
      }
      return true;
   } 
   public void sys() throws IOException{
      FileReader r=new FileReader("wordsfile.txt");
      FileWriter ex=new FileWriter("example.txt");
      int i=0;
      while(i<n2){
          for(int q=0;q<arr1[i].length();q++){
            ex.write(arr1[i].charAt(q));
          }
          ex.write('-');
          i++;
      }
      char u=(char)r.read();
      while(u!='#'){
         ex.write(u);
         u=(char)r.read();
      }
      ex.write('#');
      ex.close();
      r.close();
      FileReader r1=new FileReader("example.txt");
      FileWriter ex1=new FileWriter("wordsfile.txt");
      u=(char)r1.read();
      while(u!='#'){
         ex1.write(u);
         u=(char)r1.read();
      }
      ex1.write('#');
      r.close();
      ex1.close();
      System.exit(0);
   }
}