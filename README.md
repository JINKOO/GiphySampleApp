#. Giphy App 설명
----------
- Giphy API라는 오픈 API를 사용해, gif파일의 data를 받아와, 목록 형태로 사용자에게 모든 data를 보여주고(`TrendingFragment`),
  사용자가 찜한 아이템을 목록 형식으로 보여주는 화면(`FavoriteFragment`)이 있는 App

#. 주요 기능
----------
1. `TrendingFragment`에 API로 부터 받아온 모든 data를 recyclerview에 보여줌(구현 완료)
2. `FavoriteFragment`에 `TrendingFragment`에서 사용자가 찜한 아이템 data를 recyclerview에 보여줌(아직 구현 중, 이 부분은 코드리뷰 안해주셔도 됩니다.)
3. `Room`을 사용한 offline cache 기법 사용.

#. 사용 Architecture
-------------------
-  `MVVM` with `Data Bindng`
-  `Repository Pattern`

#. 사용 AAC
----------
- `Navigation`
- `ViewModel`
- `Room`
- `LiveData`


#. 동훈's 리뷰
----------
- 뷰모델에는 Application 이 들어가면 안됩니다
- repository 에는 Application이 들어가면 안됩니다.
- repository 에서 LiveData 를 사용하는 건 안티패턴! -> Rx 또는 Coroutine 의 Flow 나 suspend 함수 사용 추천
- viewmodel 이상부터는 aac 를 제외한 모든 안드로이드 의존성을 안가지는게 맞습니다.
- 데이터 베이스는 앞에 데이터 소스 레이어를 둬서 뷰모델 생성자에서는 DataSource 인터페이스를 받고 주입은 그 인터페이스를 구현한 객체를 넣어주면 됩니다.
ViewModel(Repository(DataSource)))
- 테스트의 용이성을 늘리기 위해서는 repository 내부에서 private 하게 필드를 가지고 있는게 아닌 생성자로 받는게 좋을 것 같습니다
- ResponseTrendingGifs 대신 Mapper 를 쓰는게 좀 더 파일의 이름이 어울릴 것 같습니다.
- 응답 클래스에 보면 종종 변수명에 _ 가 들어가는데 SerializeName 을 써서 변수 명명 규칙을 맞추는게 좋을 것 같습니다.
- 패키지 구조를 보면 data 안에 domain 그리고 각 화면이 각 피쳐로 분리가 되어 있는데 data/domain/presenter 같이 대표 패키지를 나누고 안에 알맞은 파일을 넣는게 좋아보일 것 같습니다.
- refreshGiphyListFromRepository() 요청을 viewModel 이 아닌 View 에서 하는게 좋을 듯 합니다.

#. 재강's 리뷰
-------------
1.패키징
- Trending, Favorite는 피쳐인데 같은 래벨에 Data라는 (레이어 패키치) 일관성의 차이가 잇음
- View
  ㄴ Trending
  ㄴ Favorite
- Data
  ㄴ...
이런 느낌이 좋지 않을까 싶음.

2.뷰모델에 파라미터를 넣고자 ViewModelProvider.Factory를 쓰신 의도라면 잘한 선택이지만,
VM에 기본적으로 Context, Activity, Fragment , Application등 뷰와 연관된 속성을 넣지 않음.

3.private val viewModel: TrendingViewModel by activityViewModels()
- 액티비티와 쉐어할게 없는데 굳이 프래그먼트에서 쉐어드뷰모델을 쓸 이유가 없음.

4. Repo단에서 withContext같은 일은 제거 -> vm에서 할 일

5.GiphyViewHolder 클래스는 따로 파일로 분리하도록 하자, 어댑터 안에 두면 클래스 하나의 복잡도가 높아짐.

6. val imgSrcUrl = thumbNailUrl.toUri().buildUpon().scheme("https").build()
이런 작업은 이미 vm에서 가공후 전달해주면 좋을 듯 .


7.setErrorImage 에서 Error , Loading에는 Visible이 세팅돼있는데 Done에는 없음-> 반드시 항상 모든 조건문에 속성 세팅해주는게 중요함 ( 어디는 하고 어디는 안 하고 x )

8.private val giphyDatabaseEntities: LiveData<List<GiphyDatabaseEntity>> = databaseDao.getAllGiphies()
- 라이브데이터는 VM에서 

9. 기본적으로 클래스 이름만 봐도 이놈이 어떤일만 하겠구나 를 추측할 수 있게끔 네이밍 필요,
매퍼면 매퍼, 모델Vo면 VO, 등 

