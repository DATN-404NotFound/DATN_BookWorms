package com.poly.DATN_BookWorms.restControllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import com.poly.DATN_BookWorms.entities.*;
import com.poly.DATN_BookWorms.services.*;
import com.poly.DATN_BookWorms.utils.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@Slf4j
@RequestMapping("/rest/books")
public class BookRestController {
    @Autowired
    BookService bookService;

    @Autowired
    ShopService shopService;

    @Autowired
    CategoryService categoryService;
    @Autowired
    PublishingCompanyService publishingCompanyService;
    @Autowired
    SessionService service;
    @Autowired
    TypeBookService typeBookService;
    @Autowired
    WriterService writerService;

    @Autowired
    EvaluatesService evaluatesService;

    @Autowired
    ImagesBookService imagesBookService;

    @GetMapping
    public List<Book> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/ab")
    public List<Book> getAllById() {
        Account account = service.get("user");

        return bookService.findByshopidv2(account.getListOfShopOnline().get(0).getShopid());
    }

    @GetMapping("/cate/{id}")
    public List<Book> getAll5(@PathVariable("id") Integer id) {

        return bookService.getBooksByCategoryID(id);
    }

    @GetMapping("/shop")
    public List<Book> getShop(@RequestParam("shopid") Integer shopid) {

        return bookService.findByShopList(shopid);
    }

    @PutMapping("{id}")
    public Book update(@PathVariable("id") Integer id, @RequestBody Book book) {
        return bookService.update(book);
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        bookService.delete(bookId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public Book getOne(@PathVariable("id") Long id) {
        System.out.println("111111111111111" + bookService.findById(id));
        return bookService.findById(id);
    }

    @GetMapping("/names")
    public List<Category> getAllCategoryNames() {
        return categoryService.findAll();
    }

    @GetMapping("/publishingcompany")
    public ResponseEntity<List<PublishingCompany>> getAllPublishingCompany() {
        List<PublishingCompany> publishingCompany = publishingCompanyService.findAll();

        if (publishingCompany != null && !publishingCompany.isEmpty()) {
            return new ResponseEntity<>(publishingCompany, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/type")
    public List<Integer> getBookWithTypeBook(@RequestParam("listtype") String listtype) {
        String[] listty = listtype.split(",");
        List<Integer> listtypes = new ArrayList<>();
        for (String s : listty) {
            listtypes.add(Integer.parseInt(s));
        }

        return bookService.getBookWithTypeBook(listtypes);
    }

    @GetMapping("/writer")
    public List<Integer> getBookWithWriter(@RequestParam("listwriter") String listwriter) {
        String[] listty = listwriter.split(",");
        List<Integer> listtypes = new ArrayList<>();
        for (String s : listty) {
            listtypes.add(Integer.parseInt(s));
        }

        return bookService.getBookWithWriters(listtypes);
    }

    @GetMapping("/Eva")
    public List<Long> getBookWithEvaluate(@RequestParam("listeva") Integer listeva) {
        List<Book> getalllBook = getAll();
        List<Long> bookreturn = new ArrayList<>();

        for (Book book : getalllBook) {
            List<Evaluate> getAllEva = evaluatesService.getEvaByBookid(book.getBookid());
            float tong = 0;
            for (Evaluate eva : getAllEva) {
                tong += eva.rating;
            }
            tong /= getAllEva.size();
            if (tong >= listeva) {
                bookreturn.add(book.bookid);
            }
        }
        return bookreturn;
    }

    @GetMapping("/writers")
    public ResponseEntity<List<WritingMaster>> getAllWriter() {
        List<WritingMaster> writers = writerService.findAll();

        if (writers != null && !writers.isEmpty()) {
            return new ResponseEntity<>(writers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/createBook")
    public ResponseEntity<Book> createBook(@RequestBody @Valid Book book) {
        Account user = service.get("user");
        ShopOnline shoponlines = shopService.findUserId(user.getUserid());
        book.setShopid(shoponlines.getShopid());
        Book newBook = bookService.save(book);
        return ResponseEntity.ok(newBook);
    }

    @PutMapping("/delete/{bookId}")
    public ResponseEntity<Void> deleteBooks(@PathVariable Long bookId, @RequestBody Map<String, Boolean> requestBody) {
        boolean newIsActive = requestBody.get("isactive");
        bookService.deleteBook(bookId, newIsActive);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/createTypeBook")
    public ResponseEntity<TypeBook> createTypeBooks(@RequestParam("categoryid") String categoryId,
                                                    @RequestParam("bookid") String bookId) {
        TypeBook typebook = new TypeBook();
        typebook.setBookid(Integer.parseInt(bookId));
        typebook.setCategoryid(Integer.parseInt(categoryId));
        System.out.println(typebook.getCategoryid());
        typeBookService.save(typebook);
        return ResponseEntity.ok(typebook);
    }

    @PostMapping(value = "/createWriter")
    public ResponseEntity<Writer> createWriter(@RequestBody @Valid Writer writer) {
        Writer writerReturn = writerService.save(writer);
        return ResponseEntity.ok(writerReturn);
    }

    @PostMapping(value = "/uploadImages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> handleFileUploadForm(
            @RequestPart("file") MultipartFile file,
            @RequestParam("bookId") String bookId) {

        log.info("Handling request parts: {}", file);

        try {
            File f = new ClassPathResource("").getFile();
            String uploadDir = "D:/DATN/DATN_BookWorms/src/main/resources/static/SellerChannel/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!java.nio.file.Files.exists(uploadPath))
                java.nio.file.Files.createDirectories(uploadPath);

            String uniqueFileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

            Path filePath = uploadPath.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(uniqueFileName)
                    .toUriString();

            var result = Map.of(
                    "filename", uniqueFileName,
                    "fileUri", fileUri);
            ImageBook imageBook = new ImageBook();
            imageBook.setBookid(Integer.valueOf(bookId));
            imageBook.setName(uniqueFileName);
            imageBook.setTypefile("image");
            imagesBookService.save(imageBook);
            return ResponseEntity.ok().body(result);

        } catch (IOException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE
    @PostMapping(value = "/updateBook")
    public ResponseEntity<Book> updateBook(@RequestBody @Valid Book book) {
        Account user = service.get("user");
        ShopOnline shoponlines = shopService.findUserId(user.getUserid());
        book.setShopid(shoponlines.getShopid());
        book.setBookid(book.getBookid());
        Book newBook = bookService.update(book);
        return ResponseEntity.ok(newBook);
    }

    @PostMapping(value = "/updateTypeBook")
    public ResponseEntity<TypeBook> updateTypeBooks(@RequestParam("categoryid") String categoryId,
                                                    @RequestParam("bookid") String bookId) {
        TypeBook typebook = new TypeBook();
        typebook.setTypebookid(typebook.getTypebookid());
        typebook.setBookid(Integer.parseInt(bookId));
        typebook.setCategoryid(Integer.parseInt(categoryId));
        System.out.println(typebook.getCategoryid());
        typeBookService.update(typebook);
        return ResponseEntity.ok(typebook);
    }

    @PostMapping(value = "/updateWriter")
    public ResponseEntity<Writer> updateTypeBooks(@RequestBody @Valid Writer writer) {
        Writer writerReturn = new Writer();
        writer.setWriteid(writer.getWriteid());
        writerService.save(writerReturn);
        return ResponseEntity.ok(writerReturn);
    }

    @DeleteMapping("/deleteByBookId/{bookId}")
    public void deleteImagebooksByBookId(@PathVariable Long bookId) {
        imagesBookService.deleteImagebooksByBookId(bookId);
    }
    @PutMapping("/updateIsActive/{bookId}")
    public ResponseEntity<Void> updateIsActive(@PathVariable Long bookId,
                                               @RequestBody Map<String, Boolean> requestBody) {
        boolean newIsActive = requestBody.get("isactive");
        bookService.updateIsActive(bookId, newIsActive);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findbyBookId/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        Optional<Book> optionalBook = bookService.findByBookId(bookId);
        return optionalBook.map(book -> new ResponseEntity<>(book, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/findTypeBookByBookId/{bookId}")
    public ResponseEntity<List<TypeBook>> findByBookId(@PathVariable Integer bookId) {
        try {
            List<TypeBook> typeBookList = typeBookService.findByBookId(bookId);
            return ResponseEntity.ok(typeBookList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/findWriterByBookId/{bookId}")
    public ResponseEntity<List<Writer>> findByWriterId(@PathVariable Integer bookId) {
        try {
            List<Writer> typebooksList = writerService.findByBookId(bookId);
            return ResponseEntity.ok(typebooksList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
