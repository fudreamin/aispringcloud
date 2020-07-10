package com.southwind.Controller;

import com.southwind.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@RestController
@RequestMapping("/ribbon")
public class RibbonHandler {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/findAll")
    public Collection<Student> findAll(){
        return restTemplate.getForEntity("http://provider/student/findAll", Collection.class).getBody();
    }

    @GetMapping("/findAll2")
    public Collection<Student> findAll2(){
        return restTemplate.getForObject("http://provider/student/findAll",Collection.class);
    }

    @GetMapping("/findById/{id}")
    public Student findById(@PathVariable("id") long id){
        return restTemplate.getForObject("http://provider/student/findById/{id}", Student.class,id);
    }

    @PostMapping("/save")
    public void save(@RequestBody Student student){
        restTemplate.postForObject("http://provider/student/save", student, String.class);
    }

    @PutMapping("/update")
    public void update(@RequestBody Student student){
        restTemplate.postForEntity("http://provider/student/save", student, null);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        restTemplate.delete("http://provider/student/deleteById/{id}",id);
    }

//    @Value("${server.port}")
//    private String port;
//
    @GetMapping("/getPort")
    public String getPort(){
        return restTemplate.getForObject("http://provider/student/getPort", String.class);
    }
}
