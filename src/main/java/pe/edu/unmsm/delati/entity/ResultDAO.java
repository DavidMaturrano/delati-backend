/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.unmsm.delati.entity;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.unmsm.delati.config.Connection;
import pe.edu.unmsm.delati.rest.QueryREST;
import weka.clusterers.Canopy;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
import weka.clusterers.SimpleKMeans;
import weka.core.DistanceFunction;
import weka.core.EuclideanDistance;
import weka.core.Instances;
import weka.core.ManhattanDistance;
import weka.core.converters.DatabaseLoader;
import weka.clusterers.Clusterer;

/**
 *
 * @author Lenovo Legion Y520
 */
public class ResultDAO {
    Instances data;
    public ResultDAO(String Query) throws IOException{
            Connection con = new Connection();
            DatabaseLoader db_delati = con.getConnection(Query);
            
            /*if(Query.isEmpty()){
            db_delati = con.getConnection("select distinct o.htitulo_cat, o.htitulo \n" +
            "from webscraping w inner join oferta o on \n" +
            "(w.id_webscraping=o.id_webscraping) \n" +
            "where o.id_estado is null order by 1,2;");
            }else{*/
            //db_delati = con.getConnection(Query);
            //}
            System.out.println("PUNTO 1");
            data = db_delati.getDataSet();
            System.out.println("PUNTO 2");
            data = new Instances(db_delati.getDataSet());
            System.out.println("PUNTO 3");
    }
    

    public String getResult(JSONQuery request){
        if(request == null){
            return "Petición Fallida";
        }else if(request.getType().equals("kmeans")){
            return getKMeans(request);
        }else if(request.getType().equals("em")){
            return getEM(request);
        }else{
            return getCanopy(request);
        }
        
        /*SimpleKMeans model = new SimpleKMeans();
        
        //EuclideanDistance distance = new EuclideanDistance();
        ManhattanDistance distanceM = new ManhattanDistance(data);
        
        try{
            model.setNumClusters(5);
            model.buildClusterer(data);
            model.setDistanceFunction(distanceM);
            
            ClusterEvaluation clsEval = new ClusterEvaluation();
            clsEval.setClusterer(model);
            clsEval.evaluateClusterer(data);
            
            String result = clsEval.clusterResultsToString();//clsEval.clusterResultsToString();//.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;");
            
            return result;
            
        }catch(Exception e){
            System.out.println("Fallo el metodo 'getResult': "+ e);
            return "Fallo el metodo, vuelva a intentar...";
        }*/
               
    }
    
    
    public String getKMeans(JSONQuery request){
        SimpleKMeans model = new SimpleKMeans();
        
        Object distance = new Object();
        if(request.getDistance().equals("manhattan")){
            distance = new ManhattanDistance(data);
        }else if(request.getDistance().equals("euclides")){
            distance = new EuclideanDistance(data);
        }
        //EuclideanDistance distance = new EuclideanDistance();
        //ManhattanDistance distanceM = new ManhattanDistance(data);
        
        try{
            model.setNumClusters(5);
            model.buildClusterer(data);
            model.setDistanceFunction((DistanceFunction) distance);
            
            ClusterEvaluation clsEval = new ClusterEvaluation();
            clsEval.setClusterer(model);
            clsEval.evaluateClusterer(data);
            
            String result = clsEval.clusterResultsToString();//clsEval.clusterResultsToString();//.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;");
            
            return result;
            
        }catch(Exception e){
            System.out.println("Fallo el metodo 'getResult': "+ e);
            return "Fallo el metodo, vuelva a intentar 1...";
        }
    }
    
    public String getCanopy(JSONQuery request){
        Canopy model = new Canopy();
        
        try{
            model.buildClusterer(data);
            
            ClusterEvaluation clsEval = new ClusterEvaluation();
            clsEval.setClusterer(model);
            clsEval.evaluateClusterer(data);
            
            String result = clsEval.clusterResultsToString();//clsEval.clusterResultsToString();//.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;");
            
            return result;
            
        }catch(Exception e){
            System.out.println("Fallo el metodo 'getCanopy': "+ e);
            return "Fallo el metodo, vuelva a intentar 2...";
        }
    }
    
    public String getEM(JSONQuery request){
        EM model = new EM();
        
        try{
            model.buildClusterer(data);
            
            ClusterEvaluation clsEval = new ClusterEvaluation();
            clsEval.setClusterer(model);
            clsEval.evaluateClusterer(data);
            
            String result = clsEval.clusterResultsToString();//clsEval.clusterResultsToString();//.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;");
            
            return result;
            
        }catch(Exception e){
            System.out.println("Fallo el metodo 'getEM': "+ e);
            return "Fallo el metodo, vuelva a intentar 3...";
        }
    }

    private void ClustererPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
