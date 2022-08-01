package com.ozer.bookstore.api.controllers;

import com.ozer.bookstore.business.abstracts.PhotoService;
import com.ozer.bookstore.core.utilities.exceptions.PhotoNotFoundException;
import com.ozer.bookstore.core.utilities.results.DataResult;
import com.ozer.bookstore.core.utilities.results.Result;
import com.ozer.bookstore.entities.concretes.Photo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/photos")
public class PhotosController {

    private PhotoService photoService;

    public PhotosController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("add")
    public Result add(@RequestBody Photo photo) {
        return this.photoService.add(photo);
    }

    @PutMapping("update")
    public Result update(@RequestBody Photo photo) throws PhotoNotFoundException {
        return this.photoService.update(photo);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestParam("id") int id) throws PhotoNotFoundException {
        return this.photoService.deleteById(id);
    }

    @GetMapping("getById")
    public DataResult<Optional<Photo>> getById(@RequestParam(name = "id") int id) {
        return this.photoService.getById(id);
    }

    @GetMapping("getAll")
    public DataResult<List<Photo>> getAll() {
        return this.photoService.getAll();
    }
}
