
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageThreadListMineService implements AbstractListService<Authenticated, MessageThread> {

	@Autowired
	AuthenticatedMessageThreadRepository repository;


	@Override
	public Collection<MessageThread> findMany(final Request<MessageThread> request) {
		assert request != null;

		Collection<MessageThread> result = null;
		int principal = request.getPrincipal().getAccountId();
		result = this.repository.findManyMessageThreadsByUserAccountId(principal);
		//		UserAccount user = this.repository.findOneUserAccountdById(principal.getAccountId());
		//		result = this.repository.findManyMessageThreadsByUser(user);
		return result;
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "id", "title", "moment");
	}
	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;
		return true;
	}
}
