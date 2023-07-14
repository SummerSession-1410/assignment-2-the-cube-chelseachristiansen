import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;


public class rubiks2 {

    static String[][] cube = {
    {
        "r", "r", "r",
        "r", "r", "r",
        "r", "r", "r"
    },{
        "b", "b", "b",
        "b", "b", "b",
        "b", "b", "b"
    },{
        "o", "o", "o",
        "o", "o", "o",
        "o", "o", "o"
    },{
        "g", "g", "g",
        "g", "g", "g",
        "g", "g", "g"
    },{
        "y", "y", "y",
        "y", "y", "y",
        "y", "y", "y"
    },{
        "w", "w", "w",
        "w", "w", "w",
        "w", "w", "w"
    }

};

class edgedFace{
    public int current_face;
    int[] edge1 = new int[4]; //index 0 is the face and 1-3 are the elements of the face [1,0,1,2]
    int[] edge2 = new int[4];
    int[] edge3 = new int[4];
    int[] edge4 = new int[4];


    public edgedFace(int face){
        current_face = face;

        switch(face){

            case 0: 
                edge1[0] = 1;
                edge1[1] = 2;
                edge1[2] = 5;
                edge1[3] = 8;

                edge2[0] = 4;
                edge2[1] = 6;
                edge2[2] = 7;
                edge2[3] = 8;

                edge3[0] = 3;
                edge3[1] = 0;
                edge3[2] = 3;
                edge3[3] = 6;

                edge4[0] = 5;
                edge4[1] = 0;
                edge4[2] = 1;
                edge4[3] = 2;
                break;

            case 1:
                edge1[0] = 2;
                edge1[1] = 2;
                edge1[2] = 5;
                edge1[3] = 8;

                edge2[0] = 4;
                edge2[1] = 6;
                edge2[2] = 7;
                edge2[3] = 8;

                edge3[0] = 0;
                edge3[1] = 0;
                edge3[2] = 3;
                edge3[3] = 6;

                edge4[0] = 5;
                edge4[1] = 0;
                edge4[2] = 1;
                edge4[3] = 2;
                break;

            case 2:
                edge1[0] = 3;
                edge1[1] = 2;
                edge1[2] = 5;
                edge1[3] = 8;

                edge2[0] = 4;
                edge2[1] = 6;
                edge2[2] = 7;
                edge2[3] = 8;

                edge3[0] = 1;
                edge3[1] = 0;
                edge3[2] = 3;
                edge3[3] = 6;

                edge4[0] = 5;
                edge4[1] = 0;
                edge4[2] = 1;
                edge4[3] = 2;
                break;

            case 3:
                edge1[0] = 0;
                edge1[1] = 2;
                edge1[2] = 5;
                edge1[3] = 8;

                edge2[0] = 4;
                edge2[1] = 6;
                edge2[2] = 7;
                edge2[3] = 8;

                edge3[0] = 2;
                edge3[1] = 0;
                edge3[2] = 3;
                edge3[3] = 6;

                edge4[0] = 5;
                edge4[1] = 0;
                edge4[2] = 1;
                edge4[3] = 2;
                break;

            case 4:
                edge1[0] = 2;
                edge1[1] = 0;
                edge1[2] = 1;
                edge1[3] = 2;

                edge2[0] = 3;
                edge2[1] = 0;
                edge2[2] = 1;
                edge2[3] = 2;

                edge3[0] = 0;
                edge3[1] = 0;
                edge3[2] = 1;
                edge3[3] = 2;

                edge4[0] = 1;
                edge4[1] = 0;
                edge4[2] = 1;
                edge4[3] = 2;
                break;

            case 5:
                edge1[0] = 0;
                edge1[1] = 8;
                edge1[2] = 7;
                edge1[3] = 6;

                edge2[0] = 3;
                edge2[1] = 8;
                edge2[2] = 7;
                edge2[3] = 6;

                edge3[0] = 2;
                edge3[1] = 8;
                edge3[2] = 7;
                edge3[3] = 6;

                edge4[0] = 1;
                edge4[1] = 8;
                edge4[2] = 7;
                edge4[3] = 6;
                break;

        }
    }

}


public void turnFace(int index, String direction){
    edgedFace eFace = new edgedFace(index);

    String[][] copy = new String[6][9];

    for(int i = 0; i <6; i++){
        for(int j = 0; j<9; j++){
            copy[i][j] = cube[i][j];
        }
    }

    switch(direction){
        case "c":
            cube[eFace.current_face][0] = copy[eFace.current_face][6];
            cube[eFace.current_face][1] = copy[eFace.current_face][3];
            cube[eFace.current_face][2] = copy[eFace.current_face][0];
            cube[eFace.current_face][3] = copy[eFace.current_face][7];
            cube[eFace.current_face][5] = copy[eFace.current_face][1];
            cube[eFace.current_face][6] = copy[eFace.current_face][8];
            cube[eFace.current_face][7] = copy[eFace.current_face][5];
            cube[eFace.current_face][8] = copy[eFace.current_face][2];

            //edge 1 change
            cube[eFace.edge1[0]][eFace.edge1[1]] = copy[eFace.edge4[0]][eFace.edge4[1]];
            cube[eFace.edge1[0]][eFace.edge1[2]] = copy[eFace.edge4[0]][eFace.edge4[2]];
            cube[eFace.edge1[0]][eFace.edge1[3]] = copy[eFace.edge4[0]][eFace.edge4[3]];

            //edge 2 change
            cube[eFace.edge2[0]][eFace.edge2[1]] = copy[eFace.edge1[0]][eFace.edge1[1]];
            cube[eFace.edge2[0]][eFace.edge2[2]] = copy[eFace.edge1[0]][eFace.edge1[2]];
            cube[eFace.edge2[0]][eFace.edge2[3]] = copy[eFace.edge1[0]][eFace.edge1[3]];

            //edge 3 change
            cube[eFace.edge3[0]][eFace.edge3[1]] = copy[eFace.edge2[0]][eFace.edge2[1]];
            cube[eFace.edge3[0]][eFace.edge3[2]] = copy[eFace.edge2[0]][eFace.edge2[2]];
            cube[eFace.edge3[0]][eFace.edge3[3]] = copy[eFace.edge2[0]][eFace.edge2[3]];

            //edge 4 change
            cube[eFace.edge4[0]][eFace.edge4[1]] = copy[eFace.edge3[0]][eFace.edge3[1]];
            cube[eFace.edge4[0]][eFace.edge4[2]] = copy[eFace.edge3[0]][eFace.edge3[2]];
            cube[eFace.edge4[0]][eFace.edge4[3]] = copy[eFace.edge3[0]][eFace.edge3[3]];

        break;

        case "cc":
            cube[eFace.current_face][0] = copy[eFace.current_face][2];
            cube[eFace.current_face][1] = copy[eFace.current_face][5];
            cube[eFace.current_face][2] = copy[eFace.current_face][8];
            cube[eFace.current_face][3] = copy[eFace.current_face][1];
            cube[eFace.current_face][5] = copy[eFace.current_face][7];
            cube[eFace.current_face][6] = copy[eFace.current_face][0];
            cube[eFace.current_face][7] = copy[eFace.current_face][3];
            cube[eFace.current_face][8] = copy[eFace.current_face][6];

            //edge 1 change
            cube[eFace.edge1[0]][eFace.edge1[1]] = copy[eFace.edge4[0]][eFace.edge4[1]];
            cube[eFace.edge1[0]][eFace.edge1[2]] = copy[eFace.edge4[0]][eFace.edge4[2]];
            cube[eFace.edge1[0]][eFace.edge1[3]] = copy[eFace.edge4[0]][eFace.edge4[3]];

            //edge 2 change
            cube[eFace.edge2[0]][eFace.edge2[1]] = copy[eFace.edge1[0]][eFace.edge1[1]];
            cube[eFace.edge2[0]][eFace.edge2[2]] = copy[eFace.edge1[0]][eFace.edge1[2]];
            cube[eFace.edge2[0]][eFace.edge2[3]] = copy[eFace.edge1[0]][eFace.edge1[3]];

            //edge 3 change
            cube[eFace.edge3[0]][eFace.edge3[1]] = copy[eFace.edge2[0]][eFace.edge2[1]];
            cube[eFace.edge3[0]][eFace.edge3[2]] = copy[eFace.edge2[0]][eFace.edge2[2]];
            cube[eFace.edge3[0]][eFace.edge3[3]] = copy[eFace.edge2[0]][eFace.edge2[3]];

            //edge 4 change
            cube[eFace.edge4[0]][eFace.edge4[1]] = copy[eFace.edge3[0]][eFace.edge3[1]];
            cube[eFace.edge4[0]][eFace.edge4[2]] = copy[eFace.edge3[0]][eFace.edge3[2]];
            cube[eFace.edge4[0]][eFace.edge4[3]] = copy[eFace.edge3[0]][eFace.edge3[3]];
        break;
    }
}

public void showCube(){
    int ind = 0;
        for(int x = 0; x<6; x++){
            for(int y = 0; y<3; y++){
                for(int z = 0; z<3; z++){
                    System.out.print(cube[x][ind++]);
                }
                System.out.println();
            }
            ind = 0;
            System.out.println();
        }
}

public static void main(String[] args)
throws IOException
{

    Stack<String> solve = new Stack<>();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    rubiks2 RubiksCube = new rubiks2();

    boolean argsCheck = false;
    int argsRunIndex = 0;

    if(args.length > 0){
        argsCheck = true;
    }

    boolean proceed = true;

    while(proceed){
        String input;

        if(!argsCheck){
            input = reader.readLine();
            if(!input.equals("s")) solve.push(input);
            //add input to stack
        }else{
            if(argsRunIndex == args.length){
                argsCheck = false;
                input = "s";
            }else{
                input = args[argsRunIndex];
                argsRunIndex++;
            }

        }

        switch(input){
            case "u":
            RubiksCube.turnFace(4, "c");
            RubiksCube.showCube();
            break;
            case "d":
            RubiksCube.turnFace(5, "c");
            RubiksCube.showCube();
            break;
            case "r":
            RubiksCube.turnFace(0, "c");
            RubiksCube.showCube();
            break;
            case "l":
            RubiksCube.turnFace(2, "c");
            RubiksCube.showCube();
            break;
            case "f":
            RubiksCube.turnFace(1, "c");
            RubiksCube.showCube();
            break;
            case "b":
            RubiksCube.turnFace(3, "c");
            RubiksCube.showCube();
            break;

            case "u'":
            RubiksCube.turnFace(4, "cc");
            RubiksCube.showCube();
            break;
            case "d'":
            RubiksCube.turnFace(5, "cc");
            RubiksCube.showCube();
            break;
            case "r'":
            RubiksCube.turnFace(0, "cc");
            RubiksCube.showCube();
            break;
            case "l'":
            RubiksCube.turnFace(2, "cc");
            RubiksCube.showCube();
            break;
            case "f'":
            RubiksCube.turnFace(1, "cc");
            RubiksCube.showCube();
            break;
            case "b'":
            RubiksCube.turnFace(3, "cc");
            RubiksCube.showCube();
            break;

            case "s": 
            //solve case
            while(!solve.empty()){
                //moves w/o prime should have a prime
                if(solve.peek().length() < 2) System.out.print(solve.pop() + "' ");
                //moves w/ a prime should not have a prime
                else System.out.print(solve.pop().charAt(0) + " ");
            }
            break;
            case "q":
            proceed = false;
            break;
        }
    }

    
}

}