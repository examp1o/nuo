/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NuoTestModule } from '../../../test.module';
import { BulletinMySuffixComponent } from '../../../../../../main/webapp/app/entities/bulletin-my-suffix/bulletin-my-suffix.component';
import { BulletinMySuffixService } from '../../../../../../main/webapp/app/entities/bulletin-my-suffix/bulletin-my-suffix.service';
import { BulletinMySuffix } from '../../../../../../main/webapp/app/entities/bulletin-my-suffix/bulletin-my-suffix.model';

describe('Component Tests', () => {

    describe('BulletinMySuffix Management Component', () => {
        let comp: BulletinMySuffixComponent;
        let fixture: ComponentFixture<BulletinMySuffixComponent>;
        let service: BulletinMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [NuoTestModule],
                declarations: [BulletinMySuffixComponent],
                providers: [
                    BulletinMySuffixService
                ]
            })
            .overrideTemplate(BulletinMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BulletinMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BulletinMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new BulletinMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.bulletins[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
