import graph.EdgeArrayGraphImpl;
import org.junit.Assert;
import org.junit.Test;
import app.Exercises;

public class ImplTest {

    Exercises<Integer> exercises = new Exercises<>();

    public EdgeArrayGraphImpl<Integer> graph(){
        EdgeArrayGraphImpl<Integer> graph = new EdgeArrayGraphImpl<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdge(1,2);
        graph.addEdge(2,3);
        graph.addEdge(2,4);
        graph.addEdge(3,1);


        return graph;
    }

    //Test exercise c)ii)
    @Test
    public void test1(){
        EdgeArrayGraphImpl<Integer> graph = graph();
        exercises.printGraph(graph);
    }

    //Test exercise d)i)
    @Test
    public void test2(){
        EdgeArrayGraphImpl<Integer> graph = graph();
        Assert.assertEquals(1, exercises.sourceVertexes(graph));
    }

    //Test exercise d)ii)
    @Test
    public void test3(){
        EdgeArrayGraphImpl<Integer> graph = graph();
        Assert.assertArrayEquals(new Integer[]{3,4}, exercises.sinkVertexes(graph).toArray());
    }

    //Test exercise d)iii)
    @Test
    public void test4(){
        EdgeArrayGraphImpl<Integer> graph = graph();
        System.out.println(exercises.vertexDistanceTwoOrLess(1,graph));
    }

    //Test exercise d)iv)
    @Test
    public void test5(){
        EdgeArrayGraphImpl<Integer> graph = graph();
        Assert.assertFalse(exercises.isStronglyConnected(graph));
    }

}
