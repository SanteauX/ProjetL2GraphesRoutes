package fr.um3.grapheproject.application;

import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.JFrame;

import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

import com.mxgraph.layout.*;
import com.mxgraph.swing.*;

import fr.um3.grapheproject.utilitygraphe.Node;
import fr.um3.grapheproject.utilitygraphe.OutilsGraphe;

public class DisplayGraph extends JApplet {

    private static final long serialVersionUID = 2202072534703043194L;

    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

    private JGraphXAdapter<Node, DefaultWeightedEdge> jgxAdapter;

    /**
     * An alternative starting point for this demo, to also allow running this applet as an
     * application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
    	TestVisu applet = new TestVisu();
        applet.init();

        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setTitle("Visualisation of graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void init()
    {
        ListenableGraph<Node, DefaultWeightedEdge> graphe =
            new DefaultListenableGraph<>(new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class));

        // create a visualization using JGraph, via an adapter
        jgxAdapter = new JGraphXAdapter<Node, DefaultWeightedEdge>(graphe);

        setPreferredSize(DEFAULT_SIZE);
        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
        component.setConnectable(false);
        component.getGraph().setAllowDanglingEdges(false);
        
        getContentPane().add(component);
        resize(DEFAULT_SIZE);

        OutilsGraphe.getDirectedData();

        // positioning via jgraphx layouts
        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);

        // center the circle
        int radius = 100;
        layout.setX0((DEFAULT_SIZE.width / 10.0) - radius);
        layout.setY0((DEFAULT_SIZE.height / 10.0) - radius);
        layout.setRadius(radius);
        layout.setMoveCircle(true);

        layout.execute(jgxAdapter.getDefaultParent());
        // that's all there is to it!...
    }

	
}
