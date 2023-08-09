# football
this is project with my friends
2023 여름 방학때부터 시작한 황지환, 이상진, 정휘도의 협업으로 진행

2023/07/08
1차적인 로그인 화면 완성. 이후 회원가입 화면, 파이어베이스 연결 구현 예정

2023/07/10
로그인 화면에 대한 수정, 회원가입 화면 완성. 이후 파이어베이스 연결 및 버튼 통한 페이지 이동 구현 예정

2023/07/11
파이어베이스 연결 위한 코드 작성 완료, 각 페이지에서 버튼 누르면 화면 전환 위한 코드 작성 완료.
이후 회원가입 시 작성되는 데이터에 대한 저장 필요. sql 사용이나 파이어베이스에서 데이터 저장에 연동하는 것을 고려해볼 필요 있다.

2023/07/12
회의 결과, 아이디 중복 체크, 회원가입정보저장(파이어베이스), 로그인 정보 암호화 3가지를 추가적으로 구현하기로 결정

2023/07/16
파이어베이스 연동 완료. 파이어베이스 자체에는 이메일+패스워드 기능은 있으나, 아이이+패스워드 기능은 없는 상태이다. 추후 학과 프로젝트 진행 시에는 다른 방식을 채택해야 할 필요가 있다.

2023/07/19
각자 만든 것들에 대한 변수명 및 파일 명 획일화 위한 회의 진행. 또한, 3명이 동시에 관리할 수 있는 리포지토리 생성. 이후 readme에 추가해둘 예정이다.

2023/07/23
3명이서 만든 것들을 연결시키는 작업 진행중. 몇몇 오류와 이름변경, 기능 추가가 이루어질 예정이다.
databinding 중에 unresolved reference error 발생. 이는, xml 파일을 layout으로 묶어주자 해결되었다.
이후, xml파일의 id 가져오는 과정에서 통일된 id 인식하지 못하는 에러 발생 -> 기존 id로 교체시 제대로 인식하였다.

2023/07/24
연결작업 완료. 다른 2명이 각자 만든 개인 정보 등록 화면을 1개로 합쳤다.
하지만, 페이지 간에 넘어가는 작동에 있어서 오류 발생. 개인 정보 작성 화면으로 넘어가지는 과정에서 에러가 발생한다.

2023/07/25
지환이가 frame이라 적힌 곳을 AppcompatActivity()로 바꾸면 된다고 하였으나, 달라진 바 없음.
xml 파일도 수정했으나, 여전히 오류코드 존재

2023/08/09
로그인 이후 메인페이지 제작 시작. 사진 선택 및 설정하는 기능 추가 완료. 로그인 페이지에서 메인페이지로 넘어가는 기능 구현 완료.
이후 각자 세부 페이지 구현 예정. 또한, 로그인 쪽에서 몇가지 저장 기능이 추가 될 예정이다.
