package pe.edu.unmsm.delati.rest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.unmsm.delati.entity.JSONQuery;
import pe.edu.unmsm.delati.entity.Result;
import pe.edu.unmsm.delati.entity.ResultDAO;

//@CrossOrigin(origins = "*", allowedHeaders = "true")
//@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("delati")
public class QueryREST {
    
    
    //@GetMapping
    /*@RequestMapping(value="resultQuery", method=RequestMethod.GET)
    public ResponseEntity<Result> getResult() throws IOException{
        
        ResultDAO process = new ResultDAO("");
        Result queryResult = new Result();
        try{
            //String result = clsEval.clusterResultsToString();//clsEval.clusterResultsToString();
            queryResult.setResult(process.getResult(null));
            
            return ResponseEntity.ok(queryResult);
            
        }catch(Exception e){
            System.out.println("Fallo el metodo 'getResult': "+ e);
            return ResponseEntity.notFound().build();
        }
    }*/
    
    
    @RequestMapping(value="hello", method=RequestMethod.GET)
    public String hello(){
        return "Hello word";
    }
    
    @RequestMapping(value="send", method=RequestMethod.POST)
    public ResponseEntity<Result> postResult(@RequestBody JSONQuery temporal) throws IOException{
        System.out.println(temporal.toString());
        //HttpHeaders headersHTML = new HttpHeaders();
        //headersHTML.add("Access-Control-Allow-Origin", "*");
        //headersHTML.add("Access-Control-Allow-Credentials", "true");
        ResultDAO process = new ResultDAO(temporal.getQuery());
        Result queryResult = new Result();
        try{
            //String result = clsEval.clusterResultsToString();//clsEval.clusterResultsToString();
            queryResult.setResult(process.getResult(temporal));
            System.out.println(queryResult.getResult());
            
            return ResponseEntity.ok(queryResult);//.headers(headersHTML).body(queryResult);
            
        }catch(Exception e){
            System.out.println("Fallo el metodo 'getResult': "+ e);
            return ResponseEntity.notFound().build();
        }
    }
}
