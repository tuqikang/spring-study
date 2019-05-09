package test.service.impl;

import test.entity.BooksEntity;
import test.mapper.BooksMapper;
import test.service.IBooksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图书表 服务实现类
 * </p>
 *
 * @author tuqikang
 * @since 2019-05-09
 */
@Service
public class BooksServiceImpl extends ServiceImpl<BooksMapper, BooksEntity> implements IBooksService {

}
