package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.cobros.MercadoPago;
import ar.edu.unlam.tallerweb1.domain.cobros.RepositorioMercadoPago;
import ar.edu.unlam.tallerweb1.domain.ventas.Venta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("repositorioMercadoPago")
public class RepositorioMercadoPagoImpl implements RepositorioMercadoPago {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioMercadoPagoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(MercadoPago preferencia) {
        sessionFactory.getCurrentSession().save(preferencia);
    }

    @Override
    public MercadoPago obtener(MercadoPago preferencia) {
        Session session = sessionFactory.getCurrentSession();
        return (MercadoPago) session.createCriteria(MercadoPago.class)
                .add(Restrictions.eq("id", preferencia.getId()))
                .uniqueResult();
    }

    @Override
    public MercadoPago obtener(Venta venta) {
        Session session = sessionFactory.getCurrentSession();
        return (MercadoPago) session.createCriteria(MercadoPago.class)
                .add(Restrictions.eq("venta", venta))
                .uniqueResult();
    }
}
