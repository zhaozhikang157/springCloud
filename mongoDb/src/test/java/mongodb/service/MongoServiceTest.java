package mongodb.service;

import com.mongodb.MongoDbApplication;
import com.mongodb.dto.Comments;
import com.mongodb.service.MongoDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongoServiceTest.class)
public class MongoServiceTest extends MongoDbApplication {

    @Autowired
    private MongoDbService mongoDbService;

    @Test
    public void insert() {
        Comments comments = new Comments();
        comments.setLikeNum(0);
        comments.setContent("这是一个评论");
        comments.setNickName("卢本伟");
        comments.setCreateTime(new Date());
        comments.setParentid("0");
        comments.setReplyNum(0);
        comments.setStatus("1");
        comments.setUserId("10");
        comments.setActicleid("1");
        mongoDbService.insert(comments);
    }

    @Test
    public void queryAll() {
        System.out.println(mongoDbService.queryAll());
    }

    @Test
    public void queryById() {
        System.out.println(mongoDbService.queryById(""));
    }

    @Test
    public void deleteById() {
        mongoDbService.deleteById("");
    }
}
