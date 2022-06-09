# NestedSetTest
계층형 구조의 쿼리를 공부해보자! 

```json

{
    "body": {
        "id": 1,
        "shopRole": "여행",
        "lft": 1,
        "rgt": 14,
        "shops": [
            {
                "id": 2,
                "shopRole": "국내 여행",
                "lft": 2,
                "rgt": 7,
                "shops": [
                    {
                        "id": 4,
                        "shopRole": "서울",
                        "lft": 3,
                        "rgt": 4,
                        "shops": [],
                        "depth": 3
                    },
                    {
                        "id": 5,
                        "shopRole": "여수",
                        "lft": 5,
                        "rgt": 6,
                        "shops": [],
                        "depth": 3
                    }
                ],
                "depth": 2
            },
            {
                "id": 3,
                "shopRole": "해외 여행",
                "lft": 8,
                "rgt": 13,
                "shops": [
                    {
                        "id": 6,
                        "shopRole": "미국",
                        "lft": 9,
                        "rgt": 10,
                        "shops": [],
                        "depth": 3
                    },
                    {
                        "id": 7,
                        "shopRole": "프랑스",
                        "lft": 11,
                        "rgt": 12,
                        "shops": [],
                        "depth": 3
                    }
                ],
                "depth": 2
            }
        ],
        "depth": 1
    },
    "responseTime": "2022-06-09T22:21:34.7755662"
}
```
