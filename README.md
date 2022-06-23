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
