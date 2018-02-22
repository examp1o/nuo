import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { CrowdfundingWalletMySuffix } from './crowdfunding-wallet-my-suffix.model';
import { CrowdfundingWalletMySuffixService } from './crowdfunding-wallet-my-suffix.service';

@Component({
    selector: 'jhi-crowdfunding-wallet-my-suffix-detail',
    templateUrl: './crowdfunding-wallet-my-suffix-detail.component.html'
})
export class CrowdfundingWalletMySuffixDetailComponent implements OnInit, OnDestroy {

    crowdfundingWallet: CrowdfundingWalletMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private crowdfundingWalletService: CrowdfundingWalletMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCrowdfundingWallets();
    }

    load(id) {
        this.crowdfundingWalletService.find(id)
            .subscribe((crowdfundingWalletResponse: HttpResponse<CrowdfundingWalletMySuffix>) => {
                this.crowdfundingWallet = crowdfundingWalletResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCrowdfundingWallets() {
        this.eventSubscriber = this.eventManager.subscribe(
            'crowdfundingWalletListModification',
            (response) => this.load(this.crowdfundingWallet.id)
        );
    }
}
