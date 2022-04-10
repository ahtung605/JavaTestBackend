package org.example.HomeWork_6;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Konstantin Babenko
 * @create 10.04.2022
 */

public class HomeWork_6 {

    public static void main(String[] args) throws IOException {

//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//        SqlSession session = sqlSessionFactory.openSession();
//
//        db.dao.CategoriesMapper categoriesMapper = session.getMapper(db.dao.CategoriesMapper.class);
//
//        db.model.CategoriesExample example = new db.model.CategoriesExample();
//        example.createCriteria().andIdEqualTo(1);
//        List<db.model.Categories> list = categoriesMapper.selectByExample(example);
//        System.out.println(categoriesMapper.countByExample(example));
//
//        db.model.Categories categories = new db.model.Categories();
//        categories.setTitle("test");
//        categoriesMapper.insert(categories);
//        session.commit();
//
//        db.model.CategoriesExample example2 = new db.model.CategoriesExample();
//        example2.createCriteria().andTitleLike("test");
//        List<db.model.Categories> list2 = categoriesMapper.selectByExample(example2);
//        db.model.Categories categories2 = list2.get(0);
//        categories2.setTitle("test100");
//        categoriesMapper.updateByPrimaryKey(categories2);
//        session.commit();
//
//        categoriesMapper.deleteByPrimaryKey(categories2.getId());
//        session.commit();
//
//        session.close();

    }

}
