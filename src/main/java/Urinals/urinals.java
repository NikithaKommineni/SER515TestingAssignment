//Author:Nikitha
package Urinals;
import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
public class urinals{
    public static void main(String[] args)
    {

        urinals obj=new urinals();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your choice for entering input");
        System.out.println("1.Keyboard\n2.File Input");
        int ch=sc.nextInt();
        String s="";
        if(ch==1) {
            System.out.println("Enter your status of urinals");
            s = sc.next();

            int urinalCount = obj.countUrinals(s);
            if (urinalCount == -1)
                System.out.println("String is Invalid");
            else
                System.out.println("Number of Urinals available are :"+urinalCount);

          }
        else {
                    String outputfile = obj.openFile("src/urinals.dat");
            System.out.println("Successfully written output to "+outputfile);
        }
    }
    boolean goodString( String str ) { // checks to see if valid string
        if(str.matches("^[01]+$")){
           String[] s=str.split("");
           boolean isValid=true;
           int len=s.length;
           for(int i=0;i<len-1;i++)
           {
               int a=Integer.parseInt(String.valueOf(s[i]));
               int b=Integer.parseInt(String.valueOf(s[i+1]));
                if(a==1 && b==1) {
                    isValid=false;
                    break;
                }
           }
     return isValid;
        }
        else
            return false;
    }

    public int countUrinals(String str)
    {
        urinals obj=new urinals();
        boolean isvalid=obj.goodString(str);
        if(!isvalid)
            return -1;
        String[] s=str.split("");
        int count=0;
        int len=s.length;
        int status[]=new int[len];
        for(int i=0;i<len;i++)
        {
            status[i]=Integer.parseInt(String.valueOf(s[i]));
        }
        if(len==1){
            if(status[0]==0){
                count=1;
                status[0]=1;
            }
        }
        else {
            int i=0;
            if(status[i]==0 && status[i+1]!=1){
                status[i]=1;
                count++;
            }
            for(i=1;i<len-1;i++){
                if(status[i]==0 && status[i-1]!=1 && status[i+1]!=1){
                    status[i]=1;
                    count++;
                }
            }
            if(status[i]==0&&status[i-1]!=1) {
                count++;
                status[i] = 1;
            }
        }
        return count;
    }
    public String writeToFile(String outputfile,int vacancies){
        try {
            if(!outputfile.matches("^[0-9a-z./]+"))
                return "Bad Filename";
            FileWriter writer = new FileWriter(outputfile, true);
            if(writer==null)
                throw new IOException();
            BufferedWriter bw=new BufferedWriter(writer);
            if(bw==null)
                throw new IOException();

            bw.write(Integer.toString(vacancies));
            bw.newLine();
            bw.close();
            return "";
        }
        catch(IOException e){
            System.out.println("IOException");
//            e.printStackTrace();
            return "IOException";
        }
    }
    public String openFile(String filepath) {
        try{

                urinals obj=new urinals();
                //Read from input file
                File file=new File(filepath);
                if(file==null) {

                    throw new FileNotFoundException();
                }
            //opening file to get the counter
                File cfile=new File("src/counter.txt");
                if(cfile==null)
                    throw new IOException();
                Scanner c=new Scanner(cfile);
                int counter=Integer.parseInt(c.nextLine());

            //getting the output file
            String outputfile="src/rule.txt";
            if(counter!=0)
                outputfile="src/rule"+counter+".txt";


                //reading input file
                Scanner sc=new Scanner(file);
                if(!sc.hasNextLine())
                    return "File is Empty";
                while(sc.hasNextLine()){
                    String s=sc.nextLine();
                    if(s.equals("-1"))
                            break;

                    int vacancies=obj.countUrinals(s);
                   String writeToFile= obj.writeToFile(outputfile,vacancies);

                }

                //increment the counter for next output file
                FileWriter cwriter=new FileWriter("src/counter.txt");
                if(cwriter==null)
                    throw new IOException();
                cwriter.write(Integer.toString(counter+1));
                cwriter.close();
            return outputfile;

        }
        catch(FileNotFoundException e)
        {
            System.out.println("file not found");

//            e.printStackTrace();
            return "File Not Found";
        }
        catch(IOException e)
        {
            System.out.println("Error in opening file");

//            e.printStackTrace();
            return "IOException";
        }


    }

}