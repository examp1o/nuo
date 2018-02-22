import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CrowdfundingWalletMySuffix } from './crowdfunding-wallet-my-suffix.model';
import { CrowdfundingWalletMySuffixService } from './crowdfunding-wallet-my-suffix.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-crowdfunding-wallet-my-suffix',
    templateUrl: './crowdfunding-wallet-my-suffix.component.html'
})
export class CrowdfundingWalletMySuffixComponent implements OnInit, OnDestroy {
crowdfundingWallets: CrowdfundingWalletMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private crowdfundingWalletService: CrowdfundingWalletMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search'] ?
            this.activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.crowdfundingWalletService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: HttpResponse<CrowdfundingWalletMySuffix[]>) => this.crowdfundingWallets = res.body,
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
       }
        this.crowdfundingWalletService.query().subscribe(
            (res: HttpResponse<CrowdfundingWalletMySuffix[]>) => {
                this.crowdfundingWallets = res.body;
                this.currentSearch = '';
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.currentSearch = query;
        this.loadAll();
    }

    clear() {
        this.currentSearch = '';
        this.loadAll();
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCrowdfundingWallets();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CrowdfundingWalletMySuffix) {
        return item.id;
    }
    registerChangeInCrowdfundingWallets() {
        this.eventSubscriber = this.eventManager.subscribe('crowdfundingWalletListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
