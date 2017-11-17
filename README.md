# springboot
springboot
目前支持了rest风格请求。
基于swagger实现了自动发布api。
基于mytabis实现了持久层的自动生成entity、mapper，基于interceptor自动物理分页，自动枚举、日期转换。
基于AOP记录全局日志，包含运行日志、异常日志、慢查询日志到mongodb。

目前存在的问题：
springboot
 *  TODO:1.Entity的枚举返回给前台时需要添加@JsonSerialize(using = EnumJsonSerializer.class)，否则返回给前端的为Text。
 *  	 2.添加枚举时需要在EnumTypeHandler中，添加相应枚举类。（后期将扫描修改为包扫描）
 *  	 3.日期类型需要添加@DateTimeFormat(pattern = DateUtils.ISO_DATE)注解，否则接收的字符日期类型，无法传入controller。
 *  	 4.有时间需要将mybatis-gentrator生成bean自动根据参数类型，添加相应注解。 done ，但需要引包。
 *  	 5.插入或者更新时空字段不传递至持久层
 *       6.统一的日志管理，目前BaseEnumTypeHandler有无法处理的枚举时，有散落的异常。PageinnationInterceptor分页拦截器有散落的异常
 *         Reflections 反射的帮助类，有散落的异常。

logback
 * TODO:1.mongotemplate集成logback目前有些问题。
 * 	2.logback传递bean时再转换成bean时有些问题。
 * 	3.Exception无法传递至logback
 *      4.目前再使用jsonString来回互转，性能会出现问题。并且入mongo时，又把bean转换为了map。
 *      5.ControllerBehaviorAspect有一些描述，有时间需要做到mongodb的配置里去。
 *      6.MongoDBAppender的collection，有时间需要做到mongodb的配置里去。

已解决的问题
springboot
1、3、4 -- 已解决 生成entity时自动添加，但是需要手动导入包
logback
