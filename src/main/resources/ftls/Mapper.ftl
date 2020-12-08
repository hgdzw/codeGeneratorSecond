package ${BasePackageName}dao.${DaoPackageName};

import ${BasePackageName}dao.base.AdaBaseMapper;
import ${BasePackageName}dao.common.*;
import ${BasePackageName}dao.dto.*;
import ${BasePackageName}dao.${EntityPackageName}.${ClassName};
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author ${Author}
 * Date  ${Date}
 */

public interface ${ClassName}Mapper extends AdaBaseMapper<${ClassName}> {

    /**
    * 根据请求查询列表
    * @param request
    * @return
    * */
    List<${ClassName}> listByRequest(${ClassName}ListRequest  request);


    List<${ClassName}> findList(${ClassName} ${EntityName});

    ${ClassName} findById(String id);

    int insert(${ClassName} ${EntityName});

    int insertBatch(List<${ClassName}> ${EntityName}s);

    int update(${ClassName} ${EntityName});

    /**
    * 删除根据id
    * @param id
    * @return
    */
    int deleteById(@Param("id") String id);

    /**
    * 根据id列表查询列表
    * @param id
    * @return
    */
    List<${ClassName}> listByIds(@Param("ids") List<String> ids);
}