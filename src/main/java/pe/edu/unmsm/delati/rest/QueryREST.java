package pe.edu.unmsm.delati.rest;

import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.unmsm.delati.entity.JSONQuery;
import pe.edu.unmsm.delati.entity.Result;
import pe.edu.unmsm.delati.entity.ResultDAO;


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
        //System.out.println(temporal.toString());
        ResultDAO process = new ResultDAO(temporal.getQuery());
        Result queryResult = new Result();
        try{
            queryResult.setResult(process.getResult(temporal));
            //System.out.println(queryResult.getResult());
            
            return ResponseEntity.ok(queryResult);//.headers(headersHTML).body(queryResult);
            
        }catch(Exception e){
            System.out.println("Fallo el metodo 'getResult': "+ e);
            return ResponseEntity.notFound().build();
        }
    }
}
