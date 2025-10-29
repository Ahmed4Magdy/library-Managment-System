package com.example.demo.service;

import com.example.demo.Dto.BookDto;
import com.example.demo.entity.Book;
import com.example.demo.entity.Publisher;
import com.example.demo.mapper.BookMapper;
import com.example.demo.repo.BookRepository;
import com.example.demo.repo.PublisherRepository;
import com.example.demo.service.impl.BookServiceimpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {


    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private BookServiceimpl bookServiceimpl;

    private Book book;
    private BookDto dto;
    private Publisher publisher;

    @BeforeEach
    void setup() {

        publisher = new Publisher();
        publisher.setName("ahmed");

        book = new Book();
        book.setTitle("hamsa");
        book.setPublisher(publisher);

        dto = new BookDto();
        dto.setPublisherId(1L);
        dto.setTitle("bolaa");
    }


    @Test
    void addbook_ShouldReturnBookDto_WhenPublisherExists() {

        when(publisherRepository.findById(1L)).thenReturn(Optional.of(publisher)); // لازم يبقي بواحد علشان هو بيروح لكود الاصلي هيلاقي dto.grtpublishid فهمت فلازم نفس الرقم ال ف السيتب
        when(bookMapper.toEntity(dto)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(dto);

        BookDto result = bookServiceimpl.addbook(dto);

        assertNotNull(result);
        assertEquals("bolaa", result.getTitle());

    }


    @Test
    void addbook_ShouldThrowException_WhenPublisherNotFound() {
// 1L لازم تبقي واحد لان اللوجيك بتاعه ف الكود الاصلي بياخد دي تي او بابليشر اي دي
        when(publisherRepository.findById(1L)).thenReturn(Optional.empty()); // يموكيتو لما حد ينده عليك بالميثود دي او اي ميثود عندك رجهولي فاضي والمفروض ف الكود الاصلي هيحصل اكسبشن

        RuntimeException exception = assertThrows(RuntimeException.class, () -> bookServiceimpl.addbook(dto));
        assertEquals("Publisher not found", exception.getMessage());

    }



    @Test
    void getbookyById_ShouldReturnBookDto_WhenBookExists() {


        when(bookRepository.findById(2L)).thenReturn(Optional.of(book));
        when(bookMapper.toDto(book)).thenReturn(dto);

// دلوقتي كنت حاطط ف دماغي ان لما هكريت كتاب فوق السيتب يبقي كده معايا اي دي ليه بواحد وان لما هنده عليه تحت هيرجعلي الواحد ولو حطيط رقم  2 يرجعلي بايرور مفيش الكلام ده لانه بكل بساطه بيانات وهميه يعني مش بيروح يشوف ف الداتابيز عنده اي ولاكن الستيب ال فوق ده عباره عن شويه اوبجكت مش اكتر يعني مش هيفهم انه كده معاه اي دي بواحد .... يعني لو قولتله هاتلي اي دي مليون هيرجعلي ال حكمته بيه ال هو الكتاب ال حاطه ف السيتب ان بنفذ لوجيك ماليش دعوه بقي انا بعتله اي دي واحد ولا 5
        BookDto result = bookServiceimpl.getbookyById(2L);

        assertNotNull(result);

    }

    @Test
    void getbookyById_ShouldThrowException_WhenBookNotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> bookServiceimpl.getbookyById(1L));

        assertEquals("no book with id1", exception.getMessage());
    }



    @Test
    void updateBook_ShouldUpdateAndReturnDto() {
        when(bookRepository.findById(10L)).thenReturn(Optional.of(book)); // انا لما جيت قولت هات الاي دي من اليبو ده هو كد كده وهمي يعني لو قولتله هات رقم 1000 افترض انه هو موجود وفعلا هيرجع الكتاب اهم حاجه اللوجيك هيتنفذ
        doNothing().when(bookMapper).updateBookFromDto(dto, book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(dto);

        BookDto result = bookServiceimpl.updateBook(10L, dto);

        assertNotNull(result);
        assertEquals("bolaa", result.getTitle());
        verify(bookRepository, times(1)).findById(10L);
        verify(bookMapper, times(1)).updateBookFromDto(dto, book);
        verify(bookRepository, times(1)).save(book);
    }


    @Test
    void updateBook_ShouldThrowException_WhenBookNotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> bookServiceimpl.updateBook(1L, dto));

        assertEquals("no book with id1", exception.getMessage());
        verify(bookRepository, never()).save(any());
    }



    @Test
    void getAllBook_ShouldReturnListOfBooks() {
        when(bookRepository.findAll()).thenReturn(List.of(book));
        when(bookMapper.toDto(book)).thenReturn(dto);

        List<BookDto> result = bookServiceimpl.getAllBook();

        assertEquals(1, result.size());
        assertEquals("bolaa", result.get(0).getTitle());
    }

    @Test
    void deleteByBook_ShouldCallRepositoryDelete() {
        doNothing().when(bookRepository).deleteById(10L);

        bookServiceimpl.deleteByBook(10L);

        verify(bookRepository, times(1)).deleteById(10L);
    }



}
