CREATE TABLE oauth_access_token  
(  
  token_id varchar(256) comment 'MD5加密的access_token的值',  
  token blob comment 'OAuth2AccessToken.java对象序列化后的二进制数据',  
  authentication_id varchar(256) comment 'MD5加密过的username,client_id,scope',  
  user_name varchar(256) comment '登录的用户名',  
  client_id varchar(256) comment '客户端ID',  
  authentication blob comment 'OAuth2Authentication.java对象序列化后的二进制数据',  
  refresh_token varchar(256) comment 'MD5加密果的refresh_token的值'  
);  
/*
COMMENT ON COLUMN oauth_access_token.token_id IS 'MD5加密的access_token的值';  
COMMENT ON COLUMN oauth_access_token.token IS 'OAuth2AccessToken.java对象序列化后的二进制数据';  
COMMENT ON COLUMN oauth_access_token.authentication_id IS 'MD5加密过的username,client_id,scope';  
COMMENT ON COLUMN oauth_access_token.user_name IS '登录的用户名';  
COMMENT ON COLUMN oauth_access_token.client_id IS '客户端ID';  
COMMENT ON COLUMN oauth_access_token.authentication IS 'OAuth2Authentication.java对象序列化后的二进制数据';  
COMMENT ON COLUMN oauth_access_token.refresh_token IS 'MD5加密果的refresh_token的值';  
  */
	
CREATE TABLE oauth_approvals  
(  
  userid varchar(256) comment '登录的用户名',  
  clientid varchar(256) comment '客户端ID',  
  scope varchar(256) comment '申请的权限',  
  status varchar(10) comment '状态（Approve或Deny）',  
  expiresat timestamp comment '过期时间',  
  lastmodifiedat timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最终修改时间'
);  
/*
COMMENT ON COLUMN oauth_approvals.userid IS '登录的用户名';  
COMMENT ON COLUMN oauth_approvals.clientid IS '客户端ID';  
COMMENT ON COLUMN oauth_approvals.scope IS '申请的权限';  
COMMENT ON COLUMN oauth_approvals.status IS '状态（Approve或Deny）';  
COMMENT ON COLUMN oauth_approvals.expiresat IS '过期时间';  
COMMENT ON COLUMN oauth_approvals.lastmodifiedat IS '最终修改时间';  
  */
	
CREATE TABLE oauth_client_details  
(  
  client_id varchar(256) NOT NULL comment '客户端ID',  
  resource_ids varchar(256) comment '资源ID集合,多个资源时用逗号(,)分隔',  
  client_secret varchar(256) comment '客户端密匙',  
  scope varchar(256) comment '客户端申请的权限范围',  
  authorized_grant_types varchar(256) comment '客户端支持的grant_type',  
  web_server_redirect_uri varchar(256) comment '重定向URI',  
  authorities varchar(256) comment '客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔',  
  access_token_validity integer comment '访问令牌有效时间值(单位:秒)',  
  refresh_token_validity integer comment '更新令牌有效时间值(单位:秒)',  
  additional_information varchar(4096) comment '预留字段',  
  autoapprove varchar(256) comment '用户是否自动Approval操作',  
  CONSTRAINT oauth_client_details_pkey PRIMARY KEY (client_id)  
);  
/*
COMMENT ON COLUMN oauth_client_details.client_id IS '客户端ID';  
COMMENT ON COLUMN oauth_client_details.resource_ids IS '资源ID集合,多个资源时用逗号(,)分隔';  
COMMENT ON COLUMN oauth_client_details.client_secret IS '客户端密匙';  
COMMENT ON COLUMN oauth_client_details.scope IS '客户端申请的权限范围';  
COMMENT ON COLUMN oauth_client_details.authorized_grant_types IS '客户端支持的grant_type';  
COMMENT ON COLUMN oauth_client_details.web_server_redirect_uri IS '重定向URI';  
COMMENT ON COLUMN oauth_client_details.authorities IS '客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔';  
COMMENT ON COLUMN oauth_client_details.access_token_validity IS '访问令牌有效时间值(单位:秒)';  
COMMENT ON COLUMN oauth_client_details.refresh_token_validity IS '更新令牌有效时间值(单位:秒)';  
COMMENT ON COLUMN oauth_client_details.additional_information IS '预留字段';  
COMMENT ON COLUMN oauth_client_details.autoapprove IS '用户是否自动Approval操作';  
  */
	
CREATE TABLE oauth_client_token  
(  
  token_id varchar(256) comment 'MD5加密的access_token值',  
  token blob comment 'OAuth2AccessToken.java对象序列化后的二进制数据',  
  authentication_id varchar(256) comment 'MD5加密过的username,client_id,scope',  
  user_name varchar(256) comment '登录的用户名',  
  client_id varchar(256) comment '客户端ID'  
);  
/*
COMMENT ON COLUMN oauth_client_token.token_id IS 'MD5加密的access_token值';  
COMMENT ON COLUMN oauth_client_token.token IS 'OAuth2AccessToken.java对象序列化后的二进制数据';  
COMMENT ON COLUMN oauth_client_token.authentication_id IS 'MD5加密过的username,client_id,scope';  
COMMENT ON COLUMN oauth_client_token.user_name IS '登录的用户名';  
COMMENT ON COLUMN oauth_client_token.client_id IS '客户端ID';  
  */
	
CREATE TABLE oauth_code  
(  
  code varchar(256) comment '授权码(未加密)',  
  authentication blob -- AuthorizationRequestHolder.java对象序列化后的二进制数据  
);  
/*
COMMENT ON COLUMN oauth_code.code IS '授权码(未加密)';  
COMMENT ON COLUMN oauth_code.authentication IS 'AuthorizationRequestHolder.java对象序列化后的二进制数据';  
  */
	
CREATE TABLE oauth_refresh_token  
(  
  token_id varchar(256) comment 'MD5加密过的refresh_token的值',  
  token blob comment 'OAuth2RefreshToken.java对象序列化后的二进制数据',  
  authentication blob comment 'OAuth2Authentication.java对象序列化后的二进制数据'  
);  
/*
COMMENT ON COLUMN oauth_refresh_token.token_id IS 'MD5加密过的refresh_token的值';  
COMMENT ON COLUMN oauth_refresh_token.token IS 'OAuth2RefreshToken.java对象序列化后的二进制数据';  
COMMENT ON COLUMN oauth_refresh_token.authentication IS 'OAuth2Authentication.java对象序列化后的二进制数据';  
*/


/*
INSERT INTO oauth_client_details(client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)  
    VALUES ('oauth_client_ren', null, 'oauth_client_secret_ren', 'read,write', 'authorization_code,refresh_token', 'http://localhost:8080/login', 'ROLE_USER', 1800, 86400, null, false); 
*/