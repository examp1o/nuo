/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { NuoTestModule } from '../../../test.module';
import { CrowdfundingWalletMySuffixDialogComponent } from '../../../../../../main/webapp/app/entities/crowdfunding-wallet-my-suffix/crowdfunding-wallet-my-suffix-dialog.component';
import { CrowdfundingWalletMySuffixService } from '../../../../../../main/webapp/app/entities/crowdfunding-wallet-my-suffix/crowdfunding-wallet-my-suffix.service';
import { CrowdfundingWalletMySuffix } from '../../../../../../main/webapp/app/entities/crowdfunding-wallet-my-suffix/crowdfunding-wallet-my-suffix.model';
import { ProjectMySuffixService } from '../../../../../../main/webapp/app/entities/project-my-suffix';

describe('Component Tests', () => {

    describe('CrowdfundingWalletMySuffix Management Dialog Component', () => {
        let comp: CrowdfundingWalletMySuffixDialogComponent;
        let fixture: ComponentFixture<CrowdfundingWalletMySuffixDialogComponent>;
        let service: CrowdfundingWalletMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [NuoTestModule],
                declarations: [CrowdfundingWalletMySuffixDialogComponent],
                providers: [
                    ProjectMySuffixService,
                    CrowdfundingWalletMySuffixService
                ]
            })
            .overrideTemplate(CrowdfundingWalletMySuffixDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CrowdfundingWalletMySuffixDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CrowdfundingWalletMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new CrowdfundingWalletMySuffix(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.crowdfundingWallet = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'crowdfundingWalletListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new CrowdfundingWalletMySuffix();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.crowdfundingWallet = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'crowdfundingWalletListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
