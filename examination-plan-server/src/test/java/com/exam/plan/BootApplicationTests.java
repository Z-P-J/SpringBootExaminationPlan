package com.exam.plan;

import com.exam.plan.entity.MajorCategory;
import com.exam.plan.mapper.MajorCategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

import static com.exam.plan.mapper.MajorCategoryMapper.*;

@SpringBootTest
class BootApplicationTests {

    @Resource
    public MajorCategoryMapper majorCategoryService;

    @Test
    void listAllWithCategoryTest() {
        List<MajorCategory> list = this.majorCategoryService.listAllWithCategory();
        System.out.println(list);
    }
}
