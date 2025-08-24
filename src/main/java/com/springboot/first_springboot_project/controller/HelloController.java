package com.springboot.first_springboot_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Ada yang lain seperti @PathVariable, @RequestParam, & @RequestBody
// 1. @PathVariable -> Mengambil nilai dari segmen URL (cth: /users/{id})
// 2. @RequestParam -> Mengambil nilai dari parameter query (cth: /search?keyword=buku)
// 3. @RequestBody -> Mengambil data JSON dari body permintaan (biasanya digunakan di POST dan PUT)

@RestController // Memberitahu Spring mengembalikan data (biasanya dalam format JSON)
@RequestMapping("/") // Route request yang disediakan
public class HelloController {
    
    @GetMapping// Method yang digunakan dalam route tersebut (@PostMapping, @PutMapping, @DeleteMapping)
    public String hello(){
        return "Hello, World!";
    }

}
