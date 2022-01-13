/*
 Navicat Premium Data Transfer

 Source Server         : localpg
 Source Server Type    : PostgreSQL
 Source Server Version : 130004
 Source Host           : localhost:5432
 Source Catalog        : wpm-server
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 130004
 File Encoding         : 65001

 Date: 13/01/2022 13:41:07
*/


-- ----------------------------
-- Sequence structure for user_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."user_id_seq";
CREATE SEQUENCE "public"."user_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for wpm_package_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."wpm_package_id_seq";
CREATE SEQUENCE "public"."wpm_package_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS "public"."user";
CREATE TABLE "public"."user" (
  "id" int4 NOT NULL GENERATED ALWAYS AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
),
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."user"."password" IS '密码';

-- ----------------------------
-- Table structure for wpm
-- ----------------------------
DROP TABLE IF EXISTS "public"."wpm";
CREATE TABLE "public"."wpm" (
  "id" int4 NOT NULL GENERATED BY DEFAULT AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
),
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "version" varchar(255) COLLATE "pg_catalog"."default",
  "repo" varchar(255) COLLATE "pg_catalog"."default",
  "author" varchar(255) COLLATE "pg_catalog"."default",
  "create_at" timestamp(6),
  "user_id" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."wpm"."name" IS '库名称';
COMMENT ON COLUMN "public"."wpm"."version" IS '版本信息';
COMMENT ON COLUMN "public"."wpm"."author" IS '作者信息';
COMMENT ON COLUMN "public"."wpm"."create_at" IS '创建时间';
COMMENT ON COLUMN "public"."wpm"."user_id" IS '创建用户的id';

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."user_id_seq"
OWNED BY "public"."user"."id";
SELECT setval('"public"."user_id_seq"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."wpm_package_id_seq"
OWNED BY "public"."wpm"."id";
SELECT setval('"public"."wpm_package_id_seq"', 2, false);

-- ----------------------------
-- Primary Key structure for table user
-- ----------------------------
ALTER TABLE "public"."user" ADD CONSTRAINT "user_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table wpm
-- ----------------------------
ALTER TABLE "public"."wpm" ADD CONSTRAINT "wpm_package_pkey" PRIMARY KEY ("id");
