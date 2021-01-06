<#--package ${BasePackageName}${InterfacePackageName};-->
package ${BasePackageName}service.biz;
import ${BasePackageName}dao.common.*;
import ${BasePackageName}dao.dto.*;
import ${BasePackageName}dao.${EntityPackageName}.${ClassName};

import java.util.List;

/**
 * Author ${Author}
 * Date  ${Date}
 */
public interface ${ClassName}Service {

    /**
    * 根据请求获取值班列表
    * @param request
    * @return
    */
    PageResult<${ClassName}ListResponse> listPageByRequest(PageInfo<${ClassName}ListRequest> request);


    List<${ClassName}> findList(${ClassName} ${EntityName});

    ${ClassName} findById(String id);

    /**
    * 根据id 获取详情返回
    * @param id
    * @return
    */
    ${ClassName}Response findDetailById(String id);

    int insert(${ClassName} ${EntityName});

    int create(${ClassName}Request request);

    int insertBatch(List<${ClassName}> ${EntityName}s);

    int update(${ClassName} ${EntityName});

    int updateDetail(${ClassName}Request request);

    boolean deleteById(String id);

    /**
    * 根据id获取列表
    * @param ids
    * @return
    */
    List<${ClassName}> listByIds(List<String> ids);

}