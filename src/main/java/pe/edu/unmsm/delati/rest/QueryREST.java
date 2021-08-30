package pe.edu.unmsm.delati.rest;

import java.io.IOException;
import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.unmsm.delati.entity.JSONQuery;
import pe.edu.unmsm.delati.entity.Node;
import pe.edu.unmsm.delati.entity.Result;
import pe.edu.unmsm.delati.entity.ResultDAO;


@RestController
@RequestMapping("delati")
public class QueryREST {
    
    
    @RequestMapping(value="hello", method=RequestMethod.GET)
    public String hello(){
        return "Hello word";
    }
    
    @RequestMapping(value="cobweb", method=RequestMethod.POST)
    public ResponseEntity<ArrayList<Node>> postResult(@RequestBody JSONQuery temporal) throws IOException{
        ResultDAO process = new ResultDAO(temporal.getQuery());
        try{
            return ResponseEntity.ok(process.getResult(temporal));//.headers(headersHTML).body(queryResult);
            
        }catch(Exception e){
            System.out.println("Fallo el metodo 'getResult': "+ e);
            return ResponseEntity.notFound().build();
        }
    }
}
