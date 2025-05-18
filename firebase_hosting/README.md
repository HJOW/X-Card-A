# 사용법

## firebase tools 설치 (최초 1회)

npm install -g firebase-tools

## firebase 로그인 (최초 1회) - /firebase_hosting 디렉토리에서 실행

firebase login

## X-Card 소스 편집 후 해야 할 작업

1. /src/main/assets 폴더에서 다음 명령어를 실행하여 타입스크립트 빌드

npm run build

2. /src/main/assets 폴더 내 파일들 중 index.html 을 제외한 파일들을 /firebase_hosting 로 복사

3. 파이어베이스 호스팅에 배포

npm run deploy