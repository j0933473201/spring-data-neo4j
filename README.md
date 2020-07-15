# Spring DATA Neo4j

* Spring Data Neo4j(SDN) 是 Spring Data為Neo4j圖形資料庫提供的擴充資源，可以簡化專案開發，主要包括兩個部分。
    1.  **將Neo4j的data access 集成到Spring Data中，整合下層OGM框架，提供Repository 資料訪問方式。**
    2. **統一資料庫存取的交易行為管理，由Spring來處理。**

* **分析spring-boot-stater-data-neo4j**
* **一般節點標籤註記**: 
    * @NodeEntity: 
        * 代表一個節點物件，如果不加參數，實體Label就是class名
    * @Property: 
        * 代表節點的屬性
    * @Properties: 
        * 代表Map<>屬性,OGM支持Vector,Set,List,Map等，但只有Map使用@Properties，其他均使用@Property；Map中的所有參數都會轉換成節點的屬性值
    * @Convert: 
        * 支援自動轉換，將一組內聚的屬性轉換為某個物件實體，自定義轉換實體，來處理轉換邏輯
    * 範例如：
        * ![](https://i.imgur.com/yUWyxVN.png)

* **關聯關係節點標籤註記**
    * @Relationship
        1. 一般情況下，不需要創建Relationship物件，通過@Relationship註解，可以直接標識一個關係的節點
        2. @Relationship可以作用在一個屬性上，也可以作用在一個集合上
        3. @Relationship可以跟一個direction參數，來指定關係的方向
        4. 範例如下![](https://i.imgur.com/roXwwkf.png)


* **關聯關係物件節點**
    * 通常不需要為關係建立具體的對應關係物件
    * 如果關係比較複雜，需要帶屬性，則需要創建對應的類別，用@RelationshipEntity來標註
    * @RelationshipEntity的type需要和在NodeEntity中 @Relationship type的指定的相同
    * 所有的用@RelationshipEntity來標註的關係類，必須有@Property標註的屬性，否則OGM不會進行映射
    * 所有關係物件必須指定@StartNode和@EndNode

* 自定義Cypher: 
    * Spring Data Neo4j同樣支持Cypher語句查詢，可以使用@Query註解來完成
        * @Query("MATCH (:Customer {customerId:{0}})-[r:HOLD]->(p:Product) RETURN p")

* **問題與解決**
    * 問題1
        * (自然人)-[持有股份{percentage:45}]-(法人)
        * (法人)-[持有股份{percentage:45}]-(法人)
        * 因此在製作RelationshipEntity時，必須標注@StartNode以及@EndNode,但自然人與法人是不同的物件,無法同時設定兩個@StartNode或是@EndNode
    * 解決方式:
        * 在設計NodeEntity時,製作一個BaseNodeEntity讓其他NodeEntity作為繼承,因此,在製作關聯關係時,@StartNode及@EndNode物件型別可以設置成BaseNodeEntity。



建立完成後圖示：
![](https://i.imgur.com/qzEQ0or.png)











