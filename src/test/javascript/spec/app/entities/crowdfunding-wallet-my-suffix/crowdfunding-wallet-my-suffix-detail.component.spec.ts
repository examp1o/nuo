/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { NuoTestModule } from '../../../test.module';
import { CrowdfundingWalletMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/crowdfunding-wallet-my-suffix/crowdfunding-wallet-my-suffix-detail.component';
import { CrowdfundingWalletMySuffixService } from '../../../../../../main/webapp/app/entities/crowdfunding-wallet-my-suffix/crowdfunding-wallet-my-suffix.service';
import { CrowdfundingWalletMySuffix } from '../../../../../../main/webapp/app/entities/crowdfunding-wallet-my-suffix/crowdfunding-wallet-my-suffix.model';

describe('Component Tests', () => {

    describe('CrowdfundingWalletMySuffix Management Detail Component', () => {
        let comp: CrowdfundingWalletMySuffixDetailComponent;
        let fixture: ComponentFixture<CrowdfundingWalletMySuffixDetailComponent>;
        let service: CrowdfundingWalletMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [NuoTestModule],
                declarations: [CrowdfundingWalletMySuffixDetailComponent],
                providers: [
                    CrowdfundingWalletMySuffixService
                ]
            })
            .overrideTemplate(CrowdfundingWalletMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CrowdfundingWalletMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CrowdfundingWalletMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new CrowdfundingWalletMySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.crowdfundingWallet).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
