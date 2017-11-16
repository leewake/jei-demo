# jei-demo
Demo project for Jpa Entity Inheritance

#Introduction
1.Jpa entity inheritance refer to :

[JPA实体继承实体的映射策略](http://blog.csdn.net/mhmyqn/article/details/37996673)

2.Use a factory method to search different person
Person, Male, Female

3.This is a spring boot application

#Memo
1.The annotation @Autowired is not used in a <font color="red">static method</font>. For more details, please see code <font color="red">PersonServiceFactory.setMaleSevice(MaleServiceImpl)</font>.

2.Create the <font color="red">PersonEntity</font>, due to using the strategy <font color="red">@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)</font>, so the type of geneating id should be <font color="blue">GenerationType.TABLE</font>. This leads to create a extra table <font color="red">hibernate_sequences</font>. More deatils, please see :
[cannot-use-identity-column-key-generation-with-union-subclass-table-per-clas](https://stackoverflow.com/questions/916169/cannot-use-identity-column-key-generation-with-union-subclass-table-per-clas).

3.Search persoon will serach all entity (including person, male and female which have same id).

<font color="red">search person's sql</font>

select personenti0_.id as id1_2_0_, personenti0_.name as name2_2_0_, personenti0_.sex as sex3_2_0_, personenti0_.female as female1_0_0_, personenti0_.male as male1_1_0_, personenti0_.clazz_ as clazz_0_ from ( select id, name, sex, null as female, null as male, 0 as clazz_ from person union select id, name, sex, female, null as male, 1 as clazz_ from female union select id, name, sex, null as female, male, 2 as clazz_ from male ) personenti0_ where personenti0_.id=1

<font color="red">search male's sql</font>


select maleentity0_.id as id1_2_0_, maleentity0_.name as name2_2_0_, maleentity0_.sex as sex3_2_0_, maleentity0_.male as male1_1_0_ from male maleentity0_ where maleentity0_.id=1

<font color="red">search female's sql</font>

select femaleenti0_.id as id1_2_0_, femaleenti0_.name as name2_2_0_, femaleenti0_.sex as sex3_2_0_, femaleenti0_.female as female1_0_0_ from female femaleenti0_ where femaleenti0_.id=?


