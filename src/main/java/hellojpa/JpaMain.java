package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    private EntityManager em;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //조회
            // Member findMember = em.find(Member.class, 2L);
            //    System.out.println("findMember.getId()"+ findMember.getId());
            // System.out.println("findMember.getId()"+ findMember.getName());
            // JPA 잘못된것이다. 쉽게 생각하시면 데이터 베이스 커넥션을 박았다 생각해라

            //삭제 em.remove(findMember);

            //수정은 기가막히다 그냥 이것만 넣어도 바뀐다 펄시트 안해도 된다. jpa 가 커밋할때 다 체크를 하는 것이다 .이게 짱편한 것이다.
            //findMember.setName("helloJPA");


            //List<Member> result  = em.createQuery("select m from Member as m", Member.class)
            //.setFirstResult(5).setMaxResults(8)
            //.getResultList();
            //페이징 처리 ㄱㄴ

            // 대상이 테이블이 아니고 쿼리가 되는것이다

            //   for (Member member: result){
            //       System.out.println("member.getName()"+ member.getName());
            //  }


            // 비용속 상태
          /* Member member = new Member();
           member.setId(101L);
           member.setName("HelloJPA");*/

            //영속 실제로 디비 저장은 안된다.
        /* System.out.println("Before");
            em.persist(member);
           System.out.println("after");*/

            //캐싱지원 쿼리 실행전 조회한다. , 2번째 걸 조회 하니까 1
            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);
           
           //동일성 보장
            System.out.println(findMember1 == findMember2);


            //디비 쿼리가 날라가는시기
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        // 엔티티 메니저를 꼭 닫아주어야 한다 .. 사실은 이런 코드가 없어질 것이다 스프링이 다 관리해주어서


        //code 코드를 항상 닫아 주어야 한다. 실제로 디비에 저장하는 트랜젝션은 ?? 디비 컬렉션을 얻어서 일관적인 엔티티 메니저를
        // 꼭 만들어 줘야한다.
        emf.close();


    }
}
