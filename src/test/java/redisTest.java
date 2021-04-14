import com.echo.calendar.CalendarApplication;
import com.echo.calendar.entity.pojo.UserEntity;
import com.echo.calendar.util.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;



@SpringBootTest(classes = CalendarApplication.class)
public class redisTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtil redisUtil;






    @Test
    public void test() throws JsonProcessingException{


        redisTemplate.opsForValue().set("user","userEntity2");
        System.out.println(redisTemplate.opsForValue().get("user"));


    }
}
