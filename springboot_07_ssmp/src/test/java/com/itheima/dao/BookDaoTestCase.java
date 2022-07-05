package com.itheima.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookDaoTestCase {

    @Autowired
    private BookDao bookDao;

    @Test
    void testGetById(){
        Book book = bookDao.selectById(1);
        System.out.println(book);
    }

    @Test
    void testSave(){
        Book book = new Book();
        book.setType("测试数据类型");
        book.setName("测试数据名称");
        book.setDescription("测试数据描述");
        bookDao.insert(book);
    }

    @Test
    void testUpdate(){
        Book book = new Book();
        book.setId(15);
        book.setType("测试数据123");
        book.setName("测试数据123");
        book.setDescription("测试数据123");
        bookDao.updateById(book);
    }

    @Test
    void testDelete(){
        bookDao.deleteById(16);
    }

    @Test
    void testGetAll(){
        bookDao.selectList(null);
    }

    @Test
    void testGetPage(){
        //使用mybatis-plus必须要配置拦截器  MPConfig中
        IPage page = new Page(2,5);
        bookDao.selectPage(page,null);
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
    }

    @Test
    void testGetBy(){
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","Spring");
        bookDao.selectList(queryWrapper);
    }

    @Test
    void testGetBy2(){
        String name = "spring";
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
//        if (name != null)
//            queryWrapper.like(Book::getName,name);
        queryWrapper.like(name != null,Book::getName,name);
        bookDao.selectList(queryWrapper);
    }
}
