package test.service.impl;

import test.entity.DefinitionEntity;
import test.mapper.DefinitionMapper;
import test.service.IDefinitionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 规则定义表 服务实现类
 * </p>
 *
 * @author tuqikang
 * @since 2019-05-09
 */
@Service
public class DefinitionServiceImpl extends ServiceImpl<DefinitionMapper, DefinitionEntity> implements IDefinitionService {

}
