import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { InvestmentMySuffix } from './investment-my-suffix.model';
import { InvestmentMySuffixService } from './investment-my-suffix.service';

@Component({
    selector: 'jhi-investment-my-suffix-detail',
    templateUrl: './investment-my-suffix-detail.component.html'
})
export class InvestmentMySuffixDetailComponent implements OnInit, OnDestroy {

    investment: InvestmentMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private investmentService: InvestmentMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInInvestments();
    }

    load(id) {
        this.investmentService.find(id)
            .subscribe((investmentResponse: HttpResponse<InvestmentMySuffix>) => {
                this.investment = investmentResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInInvestments() {
        this.eventSubscriber = this.eventManager.subscribe(
            'investmentListModification',
            (response) => this.load(this.investment.id)
        );
    }
}
