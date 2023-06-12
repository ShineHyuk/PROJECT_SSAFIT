DROP DATABASE IF EXISTS `ssafit_board`;
CREATE DATABASE `ssafit_board` DEFAULT CHARACTER SET utf8mb4;

USE `ssafit_board`;

DROP TABLE IF EXISTS `exercise_part`;
CREATE TABLE `exercise_part` (
	`exercise_code` INT NOT NULL PRIMARY KEY,
    `exercise_name` VARCHAR(40) NOT NULL
)ENGINE=InnoDB;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_rank` INT DEFAULT 1,
  `user_seq` INT AUTO_INCREMENT PRIMARY KEY,
  `user_id` varchar(40) NOT NULL UNIQUE,
  `user_password` varchar(40) NOT NULL,
  `user_name` varchar(40) NOT NULL,
  `user_email` varchar(40) NOT NULL,
  `user_age` INT NOT NULL,
  `user_reg_date` TIMESTAMP DEFAULT now(),
  `user_modify_date` TIMESTAMP DEFAULT NULL,
  `exercise_code` INT NOT NULL,
  CONSTRAINT `exercise_part_fk` FOREIGN KEY (`exercise_code`) REFERENCES `exercise_part`(`exercise_code`) 
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4;

DROP TABLE IF EXISTS `user_image`;
CREATE TABLE `user_image` (
	`user_id` VARCHAR(40) NOT NULL,
	`original_image_name` VARCHAR(40),
	`image_name` VARCHAR(40),
	CONSTRAINT `user_fk1` FOREIGN KEY(`user_id`) REFERENCES `user`(`user_id`) ON DELETE CASCADE, 
    PRIMARY KEY(`user_id`) 
);


INSERT INTO `exercise_part` 
VALUES (0, '없음'), (100, '전신'), (200, '상체'), (300, '하체'), (400, '복부');



INSERT INTO `user` (user_rank, user_id, user_password, user_name, user_email, user_age, exercise_code)
VALUES (0,"ssafy", "1234", "관리자","ssafy@ssafy.com", 30, 200),
	   (1,"gustn2615", "1234", "김현수","gustn2615@ssafy.com", 30, 100),
       (1,"kimbell0415", "1234", "김종혁", "kimbell0415@ssafy.com", 27, 400),
       (1,"aa", "1234", "김아아", "aa@aa", 50, 300),
       (1, "holigoddns", "1234", "지바은", "holigoddns@ssafy.com", 25, 100),
       (1, "kimssafy", "1234", "김싸피", "kimssafy@ssafy.com", 38, 200),
       (1, "parkssafy", "1234", "박싸피", "partssafy@ssafy.com", 14, 300),
       (1, "lizamong", "1234", "박대균", "lizamong@ssafy.com", 12, 100),
       (1, "topSexyGuy", "1234", "김민우", "topSexyGuy@ssafy.com", 17, 200),
       (1, "novventa", "1234", "신택수", "novventa@kakao.com", 26, 300),
       (1, "hongWoong", "1234", "문홍웅", "hong@ssafy.com", 7, 200);
       

INSERT INTO `user_image` (user_id, original_image_name, image_name) VALUES ("ssafy", null, null);
INSERT INTO `user_image` (user_id, original_image_name, image_name) VALUES ("gustn2615", null, null);
INSERT INTO `user_image` (user_id, original_image_name, image_name) VALUES ("kimbell0415", null, null);
INSERT INTO `user_image` (user_id, original_image_name, image_name) VALUES ("aa", null, null);
INSERT INTO `user_image` (user_id, original_image_name, image_name) VALUES ("holigoddns", null, null);
INSERT INTO `user_image` (user_id, original_image_name, image_name) VALUES ("kimssafy", null, null);
INSERT INTO `user_image` (user_id, original_image_name, image_name) VALUES ("parkssafy", null, null);
INSERT INTO `user_image` (user_id, original_image_name, image_name) VALUES ("lizamong", null, null);
INSERT INTO `user_image` (user_id, original_image_name, image_name) VALUES ("topSexyGuy", null, null);
INSERT INTO `user_image` (user_id, original_image_name, image_name) VALUES ("novventa", null, null);
INSERT INTO `user_image` (user_id, original_image_name, image_name) VALUES ("hongWoong", null, null);


SELECT * FROM `user`;

DROP TABLE IF EXISTS `board`;
CREATE TABLE `board` (
	`board_id` INT AUTO_INCREMENT,
    `board_writer` VARCHAR(40) NOT NULL,
    `board_title` VARCHAR(50) NOT NULL,
    `board_content` TEXT,
    `board_viewcount` INT DEFAULT 0,
    `board_reg_date` TIMESTAMP DEFAULT now(),
    CONSTRAINT `users_fk2` FOREIGN KEY(`board_writer`) REFERENCES `user`(`user_id`) ON DELETE CASCADE,
    PRIMARY KEY(`board_id`)
);

INSERT INTO `board` (board_writer, board_title, board_content) 
VALUES ("ssafy", "안녕하세요","에티켓을 지켜주세요"),
 ("ssafy", "테스트1","내용1"),
 ("ssafy", "테스트2","내용2"),
 ("gustn2615", "운동 좀 해야겠어요", "운동을 안하니 너무 피곤하네요"),
 ("kimbell0415", "운동하니 건강한 기분이네요", "모두 운동하세요"),
 ("aa", "Hello", "I'm top health guy"),
 ("holigoddns", "저는 바보입니다", "운동밖에 모르는 바보"),
 ("parkssafy", "운동을 어떻게 해야할지 모르겠다고요?", "한 번 더 생각했나요?"),
 ("kimssafy", "허허", "운동 가기 싫어"),
  ("kimbell0415", "미안", "미안합니다"),
  ("gustn2615", "술먹고싶네요", "하지만 술은 운동에 좋지 않으니 앞으로 안 먹겠습니다"),
  ("lizamong", "나를 죽이지 못하는 고통은", "나를 더 강하게 만들 뿐"),
  ("novventa", "여친구함", "캐시必"),
  ("topSexyGuy", "뭐 드시나요?", "세번째 물어봅니다");

SELECT * FROM `board`;

DROP TABLE IF EXISTS `board_comment`;
CREATE TABLE `board_comment`(
	`comment_id` INT AUTO_INCREMENT,
    `board_id` INT NOT NULL,
    `comment_writer` VARCHAR(40) NOT NULL,
    `comment_content` TEXT,
    `comment_reg_date` TIMESTAMP DEFAULT now(),
    CONSTRAINT `user_fk3` FOREIGN KEY(`comment_writer`) REFERENCES `user`(`user_id`) ON DELETE CASCADE,
    CONSTRAINT `board_fk` FOREIGN KEY(`board_id`) REFERENCES `board`(`board_id`) ON DELETE CASCADE,
    PRIMARY KEY(`comment_id`)
);

INSERT INTO `board_comment` (board_id, comment_writer, comment_content) 
VALUES (1,"ssafy","댓글 테스트입니다."),
(4, "kimbell0415","운동좀하세요"),
(5, "gustn2615", "정말 존경스럽습니다");

SELECT * FROM `board_comment`;

DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
	`notice_id` INT AUTO_INCREMENT,
    `notice_writer` VARCHAR(40) NOT NULL,
    `notice_title` VARCHAR(50) NOT NULL,
    `notice_content` TEXT,
    `notice_viewcount` INT DEFAULT 0,
    `notice_reg_date` TIMESTAMP DEFAULT now(),
    CONSTRAINT `users_fk9` FOREIGN KEY(`notice_writer`) REFERENCES `user`(`user_id`) ON DELETE CASCADE,
    PRIMARY KEY(`notice_id`)
);

INSERT INTO `notice` (notice_writer, notice_title, notice_content) 
VALUES ("ssafy", "SSAFIT 신규 회원가입 이벤트!!", "지금 회원가입을 하시면 멋진 몸짱이 될 수 있는 PT 무료 이용권을 드립니다. \n 주변 헬스장 찾기를 통해 가까운 헬스장에 문의하세요!!"),
		("ssafy", "멀티캠퍼스에 헬스장 들어오다?","저희 멀티 캠퍼스가 2학기 여름을 맞아 멋진 몸과 마음가짐을 위해 10층 휴게공간에 여러가지 운동 기구들을 비치해 놓았습니다!! \n 우리모두 짬내서 운동해보아요!!"),
        ("ssafy", "취업을 하기위한 방법","무엇보다 취업을 위해서는 체력이 중요합니다. \n 우리모두 SSAFIT 홈페이지를 통해 체력을 길러봅시다!");
        
SELECT * FROM notice;

DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`(
	`video_id` VARCHAR(40) NOT NULL PRIMARY KEY,
    `video_title` VARCHAR(70) NOT NULL,
    `youtuber` VARCHAR(20) NOT NULL,
    `video_viewcount` INT DEFAULT 0,
	`exercise_code` INT NOT NULL,
  CONSTRAINT `exercise_part_fk2` FOREIGN KEY (`exercise_code`) REFERENCES `exercise_part`(`exercise_code`)
);

INSERT INTO `video` (video_id, video_title, youtuber, exercise_code)
VALUES ("7TLk7pscICk", "(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)", "소미핏 SOMIFIT", "400"),
('gMaB-fG4u4g', '전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]', 'Thankyou BUBU', '100'),
('myNjmnvI6x0', 'NO 층간소음 올인원 운동 - 40분 유산소운동 홈트 - 관절에 무리없이 체지방 태우기', '빅씨스 Bigsis', '100'),
('FY3sqsZksJo', '급하게 찐 살 확실히 빼드립니다 전신 칼로리 제대로 불태우는 올인원 전신운동 15분 (NO 반복 NO 층간소음)', '에이핏 afit', '100'),
('OMgkDnDOpqI', '상쾌한 아침 운동 20분 - 홈트로 하루를 건강하게 시작해 보세요', '빅씨스 Bigsis', '100'),
('ZBKtRSQJT2w', '전세계 15위 보디빌더의 ‘괴물같은 등 운동 루틴’ 공개!! 미쳤다 진짜!! [등근육 운동]', '핏블리 FITVELY', '200'),
('pJ1L4EJ1z-Q', '등 만 바뀌어도 몸이 슬림 해 보일 수 있습니다. [등 성형프로젝트 8분의 기적!]', '비타민신지니 VitaminJINY', '200'),
('4t9u85AHQR0', '요일별운동 화요일 등/허리 군살 모조리 파.괴.한.다 등운동 루틴', '힙으뜸', '200'),
('8YsjXkZ4xaA', '등살빼는 최고의 운동, 등운동 맨몸 (5mins back workout at home)', '소미핏 SomiFit', '200'),
('kETh8T3it4k', '(층간소음X, 설명O) 복근운동과 유산소를 한번에 서서하는 복근운동 1탄', '힙으뜸', '400'),
('PjGcOP-TQPE', '11자복근 복부 최고의 운동 [복근 핵매운맛]', 'Thankyou BUBU', '400'),
('QSZ0mUGO_CA', '[ENG] (층간소음X, 설명O) 짧고 굵게 복근만들기 2주 챌린지', '힙으뜸', '400'),
('zcQ16cfJN9Q', '9분! 초간단 누워서하는 11자 복근운동', 'Thankyou BUBU', '400'),
('73oaPgayP8Q', '[ENG] 딱 5일! 힙으뜸 11자 복근 만들기 챌린지', '힙으뜸', '400'),
('js8z5wIZ0wg', '(층간소음X, 설명O) 진짜 힘듦주의.. 초강력 힙으뜸 하체운동 2주 챌린지', '힙으뜸', '300'),
('pDFuLG0xrsU', '요일별운동 금요일 하체집중 근력운동 15분 루틴!', '힙으뜸', '300'),
('NDsjmxTROEo', '하체비만 11자다리 최고의 운동 [하체 핵매운맛]', 'Thankyou BUBU', '300'),
('hrXfOwoEwSI', '탄탄한 하체 만드는 필수 하체운동 5가지', '준규빌더', '300'),
('2ETU1COxR3s', '하체운동 8가지', '코리안헤라클레스 이용승', '300'),
('gC_L9qAHVJ8', '30 minute fat burning home workout for beginners', 'Body Project', '100'),
('wa1qgY8CaHA', 'Bodyweight Strength with HIIT Burnout Sets', 'FitnessBlender', '100'),
('DXGT1GBAOfc', '5 Minute Toned Arms Workout (BICEPS, TRICEPS & SHOULDERS!!)', 'Athlean-XX for Women', '200'),
('XP3dfC7SmSE', 'Upper Body Workout for Great Shoulders', 'FitnessBlender', '200'),
('R1EKAgFRe2E','10-Minute Leg Workout for Toned Legs', 'Pamela Reif', '300'),
('1919eTCoESo', '10 Min Abs Workout', 'FitnessBlender', '400'),
('1BEYPiaVobM', 'QUICK 15 Minute CARDIO & ABS workout FOR BEGINNERS', 'The Body Coach TV', '400'),
('N7W4mvOyxIk', '와...르세라핌이 ‘이 동작’으로 살을 뺐구나....[극강의 9분 전신운동]', '비타민신지니 VitaminJINY', '100'),
('UBMk30rjy0o', '20 MIN FULL BODY WORKOUT // No Equipment','Pamela Reif', '100'),
('07hl4NS5A00', '어깨운동을 잘하는데 어깨가 안 커진다고요?', '고독한갯츠비', '200'),
('URVOrAxBDq0', '🏅요일별운동🏅 금요일 형님 조직으로 돌아오십쇼.. 어깨/팔 운동', '힙으뜸', '200'),
('841gJUczmzg', 'Killer LEG Workout at Home (No Equipment)', 'NEXT Workout', '300'),
('SUPk5FFha5I', 'TOP SLIM LEG WORKOUT FOR GIRL', 'DN.Beauty Natural', '300');


SELECT * FROM `video`;

DROP TABLE IF EXISTS `video_review`;
CREATE TABLE `video_review`(
	`review_id` INT AUTO_INCREMENT PRIMARY KEY,
    `video_id` VARCHAR(40) NOT NULL,
    `review_writer` varchar(40) NOT NULL,
    `review_content` TEXT,
    `review_like` INT DEFAULT 0,
	`review_dislike` INT DEFAULT 0,
    `review_reg_date` TIMESTAMP DEFAULT now(),
	CONSTRAINT `user_fk4` FOREIGN KEY(`review_writer`) REFERENCES `user`(`user_id`) ON DELETE CASCADE,
    CONSTRAINT `video_fk1` FOREIGN KEY(`video_id`) REFERENCES `video`(`video_id`) ON DELETE CASCADE
);

INSERT INTO `video_review` (video_id, review_writer, review_content, review_dislike)
VALUES
('FY3sqsZksJo', 'topSexyGuy', '배 먹어 배', 123),
('FY3sqsZksJo', 'novventa', '네카라쿠배 즉시취업 >>>>>> edu.ssafy.com <<<<<<', 231),
('FY3sqsZksJo', 'holigoddns', '운동 하지 마세요', 8);


INSERT INTO `video_review` (video_id, review_writer, review_content)
VALUES
('FY3sqsZksJo', 'ssafy', '이거 레알 개힘들어요'),
('FY3sqsZksJo', 'ssafy', '역대급이에요 숨 차서 죽을 것 같아요'),
('FY3sqsZksJo', 'ssafy', '여기가 저승인가요? 영혼 무게만큼 감량된 것 같네요'),
('myNjmnvI6x0', 'ssafy', '층간소음 없어요!! 진짜 층간소음 제로입니다 굿!'),
('myNjmnvI6x0', 'ssafy', '유산소 효과 짱! 이 영상 따라하고 체지방 다 태웠습니다~'),
('gMaB-fG4u4g', 'ssafy', '칼로리 태우기, 급찐살 빼는데에 짱입니다!'),
('gMaB-fG4u4g', 'ssafy', '몸이 불타는 것 같아요. 전신운동 효과 짱이에요'),
('OMgkDnDOpqI', 'ssafy', '아침에 후딱 하기 좋아요. 바쁜 아침에 개운해집니다!'),
('OMgkDnDOpqI', 'ssafy', '상쾌한 아침입니다~ 아침부터 스트레칭 해주니 좋네요~'),
('ZBKtRSQJT2w', 'ssafy', '크으 몸을 보니 영상 내용에 대한 신뢰가 올라갑니다!'),
('ZBKtRSQJT2w', 'ssafy', 'good content'),
('pJ1L4EJ1z-Q', 'ssafy', '굽은 등 펴기에 최고예요! 지니쌤 영상보고 많이 도움됐습니다!!'),
('pJ1L4EJ1z-Q', 'ssafy', '최고의 운동이에요. 숨어있던 제 날개뼈를 찾았어요!'),
('4t9u85AHQR0', 'ssafy', '헬린이에게 최고입니다. 집에서 쉽게 따라할 수 있어서 좋아요!'),
('8YsjXkZ4xaA', 'ssafy', '등 힘 기르는 데 짱. 처음에는 허리 힘을 많이 써서 허리가 아팠는데 자세 교정하니까 등 힘 쓰는게 느껴져요!'),
('kETh8T3it4k',  'ssafy', '매트깔기 귀찮을 때 굿. 서서하는 복근운동이라니 최고네요'),
('kETh8T3it4k',  'ssafy', '허리 안좋은 사람한테 최고! 서서하니까 허리에 무리가 안가서 좋아요'),
('PjGcOP-TQPE',  'ssafy', '100일동안 완주했어요! 지금까지 한 복근 운동 중에서 가장 결과가 좋았습니다'),
('zcQ16cfJN9Q',  'ssafy', '헬린이에게 최고. 기초없는 사람이 따라하기에 굿이에요!'),
('zcQ16cfJN9Q',  'ssafy', '땅끄부부님 덕분에 다이어트 성공했어요. 대박입니다 진짜 감사해요!'),
('73oaPgayP8Q', 'ssafy', '역대급 복근 운동 영상 중 최고입니다'),
('js8z5wIZ0wg', 'ssafy', '마일리는 제정신일까요? 일단 저는 제정신이 아닌 것 같아요'),
('js8z5wIZ0wg',  'ssafy', '마지막 스트레칭 너무 시원해요. 땀범벅 상태로 마무리까지 완벽하게 상쾌합니다~'),
('pDFuLG0xrsU', 'ssafy', '코로나 때문에 홈트하는데 넘 좋아용. 적당히 땀나고 효과 짱입니다'),
('NDsjmxTROEo', 'ssafy', '토 할 것 같아요.. 효과는 개쩌는듯'),
('hrXfOwoEwSI',  'ssafy', '헬스장에서 따라하기 좋아요. 트레이너 저리가라 입니다'),
('hrXfOwoEwSI',  'ssafy', '감사합니다. 참고해서 몸짱되겠습니다'),
('2ETU1COxR3s',  'ssafy', '스미스머신 같습니다. 용승님 짱짱맨');


SELECT * FROM `video_review`;

DROP TABLE IF EXISTS `basket`;
CREATE TABLE `basket`(
	`basket_id` INT AUTO_INCREMENT PRIMARY KEY,
	`user_id` varchar(40) NOT NULL,
    `video_id` VARCHAR(40) NOT NULL UNIQUE,
    CONSTRAINT `users_fk5` FOREIGN KEY(`user_id`) REFERENCES `user`(`user_id`) ON DELETE CASCADE, 
	CONSTRAINT `video_fk2` FOREIGN KEY(`video_id`) REFERENCES `video`(`video_id`) ON DELETE CASCADE
);

INSERT INTO `basket`(user_id, video_id)
VALUES("ssafy", "7TLk7pscICk");

SELECT * FROM `basket`;

DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow`(
	`follow_id` INT AUTO_INCREMENT PRIMARY KEY,
	`user_id` varchar(40) NOT NULL,
    `following_id` VARCHAR(40) NOT NULL,
    CONSTRAINT `user_fk6` FOREIGN KEY(`user_id`) REFERENCES `user`(`user_id`) ON DELETE CASCADE , 
	CONSTRAINT `user_fk7` FOREIGN KEY(`following_id`) REFERENCES `user`(`user_id`) ON DELETE CASCADE
);

INSERT INTO `follow`(user_id, following_id)
VALUES("ssafy", "gustn2615"), ("ssafy", "kimbell0415");

SELECT * FROM `follow`;	

DROP TABLE IF EXISTS `user_like`;
CREATE TABLE `user_like`(
	`user_id` varchar(40) NOT NULL,
    `review_id` INT NOT NULL,
    `liked` BOOLEAN,
    `disliked` BOOLEAN,
     CONSTRAINT `user_fk8` FOREIGN KEY(`user_id`) REFERENCES `user`(`user_id`) ON DELETE CASCADE ,
     CONSTRAINT `review_fk1` FOREIGN KEY(`review_id`) REFERENCES `video_review`(`review_id`) ON DELETE CASCADE
);

SELECT * FROM `user_like`;

commit;

