package test.service.impl;

import test.entity.UserBookEntity;
import test.mapper.UserBookMapper;
import test.service.IUserBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户图书关系表 服务实现类
 * </p>
 *
 * @author tuqikang
 * @since 2019-05-09
 */
@Service
public class UserBookServiceImpl extends ServiceImpl<UserBookMapper, UserBookEntity> implements IUserBookService {

}
