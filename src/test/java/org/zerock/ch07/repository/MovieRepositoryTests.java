package org.zerock.ch07.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.ch07.entity.Movie;
import org.zerock.ch07.entity.MovieImage;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest

public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository imageRepository;

    @Test
    public void test1() {
        System.out.println("test1..............");
    }

    @Commit
    @Transactional
    @Test
    public void insertMovies() {

        IntStream.rangeClosed(1,100).forEach(i -> {

            Movie movie = Movie.builder().title("Movie...." +i).build();

            System.out.println("------------------------------------------");

            movieRepository.save(movie);

            int count = (int)(Math.random() * 5) + 1; //1,2,3,4


            for(int j = 0; j < count; j++){
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("test"+j+".jpg").build();

                imageRepository.save(movieImage);
            }


            System.out.println("===========================================");

        });
    }


    @Test
    public void testListPage(){

        PageRequest pageRequest = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC, "mno"));

        Page<Object[]> result = movieRepository.getListPage(pageRequest);

        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void testList2() {

        Object[] result = movieRepository.getListPage2();

        //System.out.println(Arrays.toString(result));

        Arrays.stream(result).forEach(arr -> System.out.println(arr));

    }
}
